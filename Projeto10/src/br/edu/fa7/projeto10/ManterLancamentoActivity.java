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
	
	private Boolean isEdit = false;
	private int index = 0;
	
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
			isEdit = i.getStringExtra("op").equals("edit");
		
		if(isEdit) {
			txDescricao.setText(i.getStringExtra("descricao"));
			txValor.setText(i.getStringExtra("valor").toString());
			index = getIntent().getIntExtra("index",0);
			if(i.getStringExtra("tipo").equals("1")) {
				rdReceita.setChecked(true);
				rdDespesa.setChecked(false);
			} else {
				rdReceita.setChecked(false);
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
			
			if(valor == null || valor.trim().equals("") || descricao == null || descricao.trim().equals("")) {
				Toast.makeText(this, "Os campos são obrigatórios", Toast.LENGTH_LONG).show();
			} else {
			
				Intent i = new Intent();
					i.putExtra("op", isEdit ? "edit" : "add");
					if(isEdit) {
						i.putExtra("index", this.index);
					}
					i.putExtra("valor", valor);
					i.putExtra("descricao", descricao);
				if(rdReceita.isChecked()) {
					i.putExtra("tipo", "1");
				} else {
					i.putExtra("tipo", "2");
				}
				setResult(RESULT_OK, i);
				finish();
			}
			break;

		default:
			break;
		}
		
	}

}
