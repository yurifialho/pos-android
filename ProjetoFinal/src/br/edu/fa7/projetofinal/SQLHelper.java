package br.edu.fa7.projetofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {
	
	private String createSQL;
	private String tableName;
	
	public SQLHelper(Context context, String dbName, String tableName, 
			int version, String createSQL) {
		super(context, dbName, null, version);
		this.createSQL = createSQL;
		this.tableName = tableName;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(this.createSQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + this.tableName);
		this.onCreate(db);
	}

}
