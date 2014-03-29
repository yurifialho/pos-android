package br.edu.fa7.projetofinal;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class CadastrarComprasActivity extends Activity implements TextWatcher, OnClickListener{
	
	private RadioGroup radioGroup;
	private EditText quantidade;
	private EditText precoUnit;
	private EditText precoTotal;
	private Button salvar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_compras);
		
		radioGroup = (RadioGroup) findViewById(R.id.rdGrpTipo);
		
		quantidade = (EditText) findViewById(R.id.txQtd);
		quantidade.addTextChangedListener(this);
		
		precoUnit = (EditText) findViewById(R.id.txVlrUnit);
		precoTotal = (EditText) findViewById(R.id.txVlrTotal);
		
		salvar = (Button) findViewById(R.id.btSalvar);
		salvar.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.cadastrar_compras, menu);
		return true;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	@Override
	public void afterTextChanged(Editable s) {
		String qtd   = quantidade.getText().toString();
		String valor = precoUnit.getText().toString();
		
		if(qtd != null && qtd.matches("[0-9]+") && valor != null && valor.matches("[0-9\\.]+")) {
			precoTotal.setText((Integer.valueOf(qtd) * Double.valueOf(valor))+"");
		} else {
			precoTotal.setText("0.0");
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btSalvar:
			
			break;

		default:
			break;
		}
	}
}
