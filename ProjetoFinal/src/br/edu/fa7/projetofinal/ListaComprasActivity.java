package br.edu.fa7.projetofinal;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListaComprasActivity extends Activity implements OnClickListener, OnItemClickListener, OnItemLongClickListener {
	
	private final int ENTRY_ADD = 1;
	
	private ListView listCompras;
	private Button btAdd;
	private TextView saldo;
	private AlertDialog dialogo;
	
	private List<Produto> produtos = new ArrayList<Produto>();
	private Produto produto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_compras);
		
		this.produtos = new ProdutoDB(this).findAll();
		
		this.listCompras = (ListView) findViewById(R.id.listViewCompras);
		this.listCompras.setAdapter(new ListaComprasAdapter(this, produtos));
		this.listCompras.setOnItemClickListener(this);
		this.listCompras.setOnItemLongClickListener(this);
		
		this.btAdd = (Button) findViewById(R.id.btAdd);
		this.btAdd.setOnClickListener(this);
		
		this.saldo = (TextView) findViewById(R.id.txSubtotal);
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == ENTRY_ADD && resultCode == RESULT_OK) {
			int index = data.getIntExtra("index", -1);
			Produto produto = (Produto) data.getSerializableExtra("produto");
			
			if(index > -1) {
				this.produtos.remove(index);
				this.produtos.add(index, produto);
			} else {
				this.produtos.add(produto);
			}
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		this.listCompras.setAdapter(new ListaComprasAdapter(this, produtos));
		calcularSaldo();
	}

	private void calcularSaldo() {
		Double saldo = 0.0;
		for(Produto p : this.produtos) {
			saldo += (p.getQuantidade() * p.getValorUnitario());
		}
		
		this.saldo.setText("Subtotal: R$ " + String.format("%.2f", saldo));
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int pos, long id) {
		Produto produto = this.produtos.get(pos);
		Intent i = new Intent(this, CadastrarComprasActivity.class);
			i.putExtra("index", pos);
			i.putExtra("produto", produto);
		
		startActivityForResult(i, ENTRY_ADD);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View view, int pos,
			long id) {
		produto = this.produtos.get(pos);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Voce deseja apagar esse item?");
			builder.setTitle("Apagar " + ListaComprasActivity.this.produto.getDescricao());
			builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					new ProdutoDB(ListaComprasActivity.this).delete(ListaComprasActivity.this.produto);
					ListaComprasActivity.this.produtos.remove(ListaComprasActivity.this.produto);
					ListaComprasActivity.this.listCompras.setAdapter(new ListaComprasAdapter(ListaComprasActivity.this, produtos));
					calcularSaldo();
				}
			});
			builder.setNegativeButton("NÌO", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					ListaComprasActivity.this.dialogo.cancel();
				}
			});
			
		this.dialogo = builder.create();
		this.dialogo.show();
		return false;
	}
}