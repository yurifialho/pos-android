package br.edu.fa7.projeto10;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ManterLancamentoActivity extends Activity implements OnClickListener {
	
	private EditText txDescricao;
	private EditText txValor;
	private RadioButton rdReceita;
	private RadioButton rdDespesa;
	private Button btSalvar;
	
	private int index = -1;
	private Lancamento l; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manter_lancamento);
		
		btSalvar = (Button) findViewById(R.id.btSalvar);
		btSalvar.setOnClickListener(this);
		
		txDescricao = (EditText) findViewById(R.id.txtipo);
		txValor   = (EditText) findViewById(R.id.txValor);
		rdReceita = (RadioButton) findViewById(R.id.rdReceita);
		rdDespesa = (RadioButton) findViewById(R.id.rdDespesa);
		
		Intent i = getIntent();
			index = i.getIntExtra("index", -1);
		
		if(index > -1) {
			l = (Lancamento) i.getSerializableExtra("lancamento");
			txDescricao.setText(l.getTipoLancamento());
			txValor.setText(l.getValue().toString());
			if(l.getIsReceita()) {
				rdReceita.setChecked(true);
			} else {
				rdDespesa.setChecked(true);
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manter_lancamento, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btSalvar:
			String valor = txValor.getText().toString();
			String descricao = txDescricao.getText().toString();
			Boolean isReceita = rdReceita.isChecked();
			
			if(valor == null || valor.trim().equals("") || descricao == null || descricao.trim().equals("")) {
				Toast.makeText(this, "Os campos são obrigatórios", Toast.LENGTH_LONG).show();
			} else {
				if(l == null) {
					l = new Lancamento(new Double(valor), isReceita, descricao);
					Long id = new LancamentoDB(this).insert(l);
					l.setId(id);
				} else {
					l.setValue(new Double(valor));
					l.setIsReceita(isReceita);
					l.setTipoLancamento(descricao);
					new LancamentoDB(this).update(l);
				}
				
				Intent i = new Intent();
					i.putExtra("lancamento", l);
					i.putExtra("index", this.index);
					
				setResult(RESULT_OK, i);
				finish();
			}
			break;

		default:
			break;
		}
		
	}

}
