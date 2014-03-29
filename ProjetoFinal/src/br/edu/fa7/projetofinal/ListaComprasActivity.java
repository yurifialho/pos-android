package br.edu.fa7.projetofinal;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListaComprasActivity extends Activity implements OnClickListener{
	
	private final int ENTRY_ADD = 1;
	
	private ListView listCompras;
	private Button btAdd;
	private TextView saldo;
	
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_compras);
		
		this.produtos = new ProdutoDB(this).findAll();
		
		Produto p = new Produto(1, 2, 34.4);
		this.produtos.add(p);
		
		this.listCompras = (ListView) findViewById(R.id.listViewCompras);
		this.listCompras.setAdapter(new ListaComprasAdapter(this, produtos));
		
		this.btAdd = (Button) findViewById(R.id.btAdd);
		this.btAdd.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_compras, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btAdd:
			Intent i = new Intent(this, CadastrarComprasActivity.class);
			startActivityForResult(i, ENTRY_ADD);
			break;
		default:
			break;
		}
	}

}
