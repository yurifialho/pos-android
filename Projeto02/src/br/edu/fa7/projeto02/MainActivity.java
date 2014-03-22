package br.edu.fa7.projeto02;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.text.method.Touch;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button btnAbrirBrowser;
	private Button btnFazerLigacao;
	private Button btnVisualizarContato;
	private Button btnListarContatos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnAbrirBrowser = (Button) findViewById(R.id.btnAbrirBrowser);
		btnFazerLigacao = (Button) findViewById(R.id.btnFazerLigacao);
		btnVisualizarContato = (Button) findViewById(R.id.btnVisualizarContato);
		btnListarContatos = (Button) findViewById(R.id.btnListarContatos);
		
		btnAbrirBrowser.setOnClickListener(this);
		btnFazerLigacao.setOnClickListener(this);
		btnVisualizarContato.setOnClickListener(this);
		btnListarContatos.setOnClickListener(this);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent it = null;
		Uri uri;
		switch (v.getId()) {
		case R.id.btnAbrirBrowser: 
			uri = Uri.parse("http://www.google.com");
			it = new Intent(Intent.ACTION_VIEW, uri);
			break;
		case R.id.btnFazerLigacao:
			uri = Uri.parse("tel:5559867");
			it = new Intent(Intent.ACTION_CALL);
			break;
		case R.id.btnVisualizarContato:
			uri = Uri.parse("content://com.android.contacts/contacts/1");
			it = new Intent(Intent.ACTION_VIEW, uri);
			break;
		case R.id.btnListarContatos:
			//uri = Uri.parse("content://com.android.contacts/contacts");
			uri = Uri.parse("content://contacts");
			it = new Intent(Intent.ACTION_PICK, uri);
			it.setType(Phone.CONTENT_TYPE);
			startActivityForResult(it, 0);
			return;
		default:
			break;
		}
		
		startActivity(it);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 0 && resultCode == RESULT_OK) {
			Uri uri = data.getData();
			
			//Intent it = new Intent(Intent.ACTION_VIEW, uri);
			
			//startActivity(it);
			
			String[] projection = {Phone.NUMBER};
			
			Cursor c = getContentResolver().query(uri, projection, null, null, null);
				c.moveToFirst();
				
			int numberIndex = c.getColumnIndex(Phone.NUMBER);
			String number = c.getString(numberIndex);
			
			Toast.makeText(this, "Tel: " + number, Toast.LENGTH_LONG).show();
		}
	}
}