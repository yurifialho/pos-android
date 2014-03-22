package br.edu.fa7.projeto10;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ExtratoActivity extends Activity implements OnItemClickListener, OnItemLongClickListener {
	
	private final int ENTRY_ADD = 1;
	
	private ListView extratoListView;
	private TextView saldo;
	
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_extrato);
		
		this.lancamentos = new LancamentoDB(this).findAll();
		
		this.extratoListView = (ListView) findViewById(R.id.extratoview);
		this.extratoListView.setAdapter(new ExtratoAdpter(this, lancamentos));
		this.extratoListView.setOnItemClickListener(this);
		this.extratoListView.setOnItemLongClickListener(this);
		
		this.saldo = (TextView) findViewById(R.id.saldo);
		calcularSaldo();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.extrato, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == ENTRY_ADD && resultCode == RESULT_OK) {
			if(data.getIntExtra("index", -1) < 0) {
				Lancamento l = (Lancamento) data.getSerializableExtra("lancamento");
				this.lancamentos.add(l);
			} else {
				this.lancamentos.remove(data.getIntExtra("index", -1));
				Lancamento l = (Lancamento) data.getSerializableExtra("lancamento");
				this.lancamentos.add(data.getIntExtra("index", -1),l);
			}
			this.extratoListView.setAdapter(new ExtratoAdpter(this, lancamentos));
			calcularSaldo();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int pos, long id) {
		Lancamento l = this.lancamentos.get(pos);
		Intent i = new Intent(this, ManterLancamentoActivity.class);
			i.putExtra("lancamento", l);
			i.putExtra("index", pos);

		startActivityForResult(i, ENTRY_ADD);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add:
			Intent i = new Intent(this, ManterLancamentoActivity.class);
				i.putExtra("index", -1);
			startActivityForResult(i, ENTRY_ADD);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void calcularSaldo() {
		Double saldo = new Double(0.0);
		for(Lancamento l : this.lancamentos) {
			if(l.getIsReceita()) {
				saldo += l.getValue();
			} else {
				saldo -= l.getValue();						
			}
		}
		
		this.saldo.setText("Saldo: R$ " + saldo.toString());
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		new LancamentoDB(this).delete(this.lancamentos.get(arg2));
		this.lancamentos.remove(arg2);
		this.extratoListView.setAdapter(new ExtratoAdpter(this, lancamentos));
		calcularSaldo();
		return false;
	}
	
}
