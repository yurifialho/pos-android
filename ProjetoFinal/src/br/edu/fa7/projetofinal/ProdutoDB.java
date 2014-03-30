package br.edu.fa7.projetofinal;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ProdutoDB {
	
	private String createTable = " CREATE TABLE produtos ( "
			+ " _id INTEGER PRIMARY KEY AUTOINCREMENT,     "
			+ " descricao VARCHAR NOT NULL,                "
			+ " qtd  INTEGER NOT NULL,                     "
			+ " valor DOUBLE NOT NULL);                    ";
	
	private final static String NOME_BANCO = "projeto_final";
	private final static int VERSAO_DB = 2;
	private final static String NOME_TABELA = "produtos";
	
	private SQLHelper helper;
	private Context contexto;
	private SQLiteDatabase db;
	
	public ProdutoDB(Context contexto) {
		this.contexto = contexto;
		
		helper = new SQLHelper(this.contexto, NOME_BANCO, NOME_TABELA, VERSAO_DB, createTable);
		db = helper.getWritableDatabase();
	}
	
	public Long insert(Produto produto) {
		ContentValues contents = new ContentValues();
			contents.put("descricao",  produto.getDescricao());
			contents.put("qtd",   produto.getQuantidade());
			contents.put("valor", produto.getValorUnitario());
		
		return db.insert(NOME_TABELA, null, contents);
	}
	
	public void update(Produto produto) {
		ContentValues contents = new ContentValues();
			contents.put("descricao",  produto.getDescricao());
			contents.put("qtd",   produto.getQuantidade());
			contents.put("valor", produto.getValorUnitario());
			
		db.update(NOME_TABELA, contents, " _id = ? ", new String[]{produto.getId().toString()});
	}
	
	public void delete(Produto produto) {
		db.delete(NOME_TABELA, " _id = ? ", new String[]{produto.getId().toString()});
	}
	
	public List<Produto> findAll() {
		Cursor c = db.query(NOME_TABELA, null, null, null, null, null, "descricao");
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		if(c != null && c.getCount() > 0) {
			c.moveToFirst();
			
			while(!c.isAfterLast()) {
				Integer id   = c.getInt(c.getColumnIndex("_id"));
				String descricao = c.getString(c.getColumnIndex("descricao"));
				Integer qtd  = c.getInt(c.getColumnIndex("qtd"));
				Double valor = c.getDouble(c.getColumnIndex("valor"));
				
				Produto p = new Produto(id, descricao, qtd, valor);
				produtos.add(p);
				c.moveToNext();
			}
		}
		
		return produtos;
	}
	
	public Produto findById(Integer id) {
		Cursor c = db.query(NOME_TABELA, null, " _id = ? ", new String[]{ id.toString() }, null, null, "_id");
		
		if(c != null && c.getCount() > 0) {
			c.moveToFirst();
			
			Integer codId   = c.getInt(c.getColumnIndex("_id"));
			String descricao = c.getString(c.getColumnIndex("descricao"));
			Integer qtd  = c.getInt(c.getColumnIndex("qtd"));
			Double valor = c.getDouble(c.getColumnIndex("valor"));
			
			return new Produto(codId, descricao, qtd, valor);
		}
		
		return null;
	}
}
