package br.edu.fa7.projeto10;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class LancamentoDB {
	private String createTable = "CREATE TABLE lancamentos ( "
			+ " _id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ " valor DOUBLE NOT NULL, "
			+ " is_receita BOOLEAN, "
			+ " descricao VARCHAR "
			+ ")";
	
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
	
	public void insert(Lancamento reg) {
		ContentValues contents = new ContentValues();
			contents.put("valor", reg.getValue());
			contents.put("is_receita", reg.getIsReceita());
			contents.put("descricao", reg.getTipoLancamento());
			
		db.insert(NOME_TABELA, null, contents);
	}
	
	public void update(Lancamento reg) {
		ContentValues contents = new ContentValues();
			contents.put("valor", reg.getValue());
			contents.put("is_receita", reg.getIsReceita());
			contents.put("descricao", reg.getTipoLancamento());
		
		db.update(NOME_TABELA, contents, " _id = ?", new String[]{ reg.getId().toString()});
	}
}
