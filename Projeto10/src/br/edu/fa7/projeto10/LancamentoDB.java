package br.edu.fa7.projeto10;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LancamentoDB {
	private String createTable = "CREATE TABLE lancamentos ( "
			+ " _id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ " valor DOUBLE NOT NULL, "
			+ " is_receita INTEGER, "
			+ " descricao VARCHAR "
			+ ");";
	
	private final static String NOME_BANCO = "cash_manager";
	private final static int VERSAO_BANCO = 1;
	private final static String NOME_TABELA = "lancamentos";
	
	private SQLHelper sqlHelper;
	private Context context;
	private SQLiteDatabase db;
	
	public LancamentoDB(Context context) {
		this.context = context;
		
		sqlHelper = new SQLHelper(context, NOME_BANCO, VERSAO_BANCO, createTable);
		db = sqlHelper.getWritableDatabase();
	}
	
	public Long insert(Lancamento reg) {
		ContentValues contents = new ContentValues();
			contents.put("valor", reg.getValue());
			contents.put("is_receita", reg.getIsReceita() ? 1 : 0);
			contents.put("descricao", reg.getTipoLancamento());
			
		return db.insert(NOME_TABELA, null, contents);
	}
	
	public void update(Lancamento reg) {
		ContentValues contents = new ContentValues();
			contents.put("valor", reg.getValue());
			contents.put("is_receita", reg.getIsReceita()? 1 : 0);
			contents.put("descricao", reg.getTipoLancamento());
		
		db.update(NOME_TABELA, contents, " _id = ?", new String[]{ reg.getId().toString()});
	}
	
	public void delete(Lancamento reg) {
		db.delete(NOME_TABELA, " _id = ?", new String[]{ reg.getId().toString()});
	}
	
	public List<Lancamento> findAll() {
		Cursor c = db.query(NOME_TABELA, new String[] {"_id","valor", "is_receita", "descricao"}, null, null, null, null, "_id");
		
		List<Lancamento> lancamentos = new ArrayList<Lancamento>();
		
		if(c != null && c.getCount() > 0) {
			c.moveToFirst();
			while(!c.isAfterLast()){
				Lancamento l = new Lancamento(c.getLong(0), c.getDouble(1), c.getInt(2) == 1 ? true : false, c.getString(3));
				lancamentos.add(l);
				c.moveToNext();
			}
		}
		
		return lancamentos;
	}
	
	public Lancamento find(Lancamento reg) {
		Cursor c = db.query(NOME_TABELA, new String[] {"_id", "is_receita", "descricao"}, "_id = ?", new String[]{ reg.getId().toString()}, null, null, null);
		
		if(c != null && c.getCount() > 0) {
			c.moveToFirst();
			Lancamento l = new Lancamento(c.getLong(0), c.getDouble(1), c.getInt(2) == 1 ? true : false, c.getString(3));
			return l;
		}
		return null;
	}
}
