package br.edu.fa7.projeto10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper{
	
	private String createSQL;
	
	public SQLHelper(Context context, String name,
			int version, String createSQL) {
		super(context, name, null, version);
		this.createSQL = createSQL;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createSQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DELETE TABLE lancamentos");
		onCreate(db);
	}
}