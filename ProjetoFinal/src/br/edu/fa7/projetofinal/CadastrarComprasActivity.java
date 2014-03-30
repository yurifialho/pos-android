package br.edu.fa7.projetofinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarComprasActivity extends Activity implements TextWatcher, OnClickListener{
	
	private EditText descricao;
	private EditText quantidade;
	private EditText precoUnit;
	private EditText precoTotal;
	private Button salvar;
	
	private Integer index;
	private Produto produto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_compras);
		
		descricao = (EditText) findViewById(R.id.txDescricao);
		
		quantidade = (EditText) findViewById(R.id.txQtd);
		quantidade.addTextChangedListener(this);
		
		precoUnit = (EditText) findViewById(R.id.txVlrUnit);
		precoUnit.addTextChangedListener(this);
		
		precoTotal = (EditText) findViewById(R.id.txTotal);
		
		salvar = (Button) findViewById(R.id.btSalvar);
		salvar.setOnClickListener(this);
		
		Intent intent = getIntent();
			index   = intent.getIntExtra("index", -1);
		if(index != null && index > -1) {
			produto = (Produto) intent.getSerializableExtra("produto");
			
			descricao.setText(produto.getDescricao());
			quantidade.setText(produto.getQuantidade().toString());
			precoUnit.setText(produto.getValorUnitario().toString());
			precoTotal.setText(String.format("%.2f",(produto.getQuantidade() * produto.getValorUnitario())));
		}
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
			precoTotal.setText(String.format("%.2f",(Integer.valueOf(qtd) * Double.valueOf(valor))));
		} else {
			precoTotal.setText(String.format("%.2f", 0.0));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btSalvar:
			Produto produto = new Produto();
				produto.setQuantidade(Integer.valueOf(quantidade.getText().toString()));
				produto.setValorUnitario(Double.valueOf(precoUnit.getText().toString()));
				produto.setDescricao(descricao.getText().toString());
			if(produto.getDescricao() == null || produto.getDescricao().isEmpty() || produto.getQuantidade() <= 0) {
				Toast.makeText(this, "Os campos devem ser preenchidos!", Toast.LENGTH_LONG).show();
				return;
			}
				
				
			if(index != null && index > -1) {
				produto.setId(this.produto.getId());
				new ProdutoDB(this).update(produto);
			} else {
				produto.setId(new ProdutoDB(this).insert(produto).intValue());
			}
			
			Intent i = new Intent();
				i.putExtra("index", index);
				i.putExtra("produto", produto);
			setResult(RESULT_OK, i);
			finish();
			break;

		default:
			break;
		}
	}
}
