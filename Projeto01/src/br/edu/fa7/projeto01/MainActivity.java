package br.edu.fa7.projeto01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	
	private Button btnActivity1;
	private Button btnActivity2;
	private Button btnProjeto02;
	
	private EditText etTexto;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        btnActivity1 = (Button) findViewById(R.id.btnActivity1);
       // btnActivity1.setOnClickListener( this);
        
        btnActivity2 = (Button) findViewById(R.id.btnActivity2);
        btnActivity2.setOnClickListener( this);
        
        
        btnProjeto02 = (Button) findViewById(R.id.btnProjeto02);
        btnProjeto02.setOnClickListener(this);
        
        etTexto = (EditText) findViewById(R.id.etTexto);
//        etTexto.onke
        
        Log.i("MainActivity", "onCreate() chamado");
    }
    
    public void clicar(View view) {
    	Log.i("MainActivity", "XXXXXXXX");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View view) {
		Intent it;
		switch (view.getId()) {
		case R.id.btnActivity1:
			it = new Intent(this, Activity1.class);
			startActivity(it);
			break;
		case R.id.btnActivity2:
			String etTextoValue = etTexto.getText().toString();
			if(!etTextoValue.isEmpty()) {
				it = new Intent(this, Activity2.class);
				it.putExtra("texto", etTextoValue);
				startActivityForResult(it, 0);
			} else {
				Toast toast = Toast.makeText(this, " Digite algum texto!", Toast.LENGTH_SHORT);
					toast.show();
			}
			break;
		case R.id.btnProjeto02:
			it = new Intent("br.edu.fa7.projeto02.RAPADURA");
			startActivity(it);
		default:
			break;
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//super.onActivityResult(requestCode, resultCode, data);
		
		if(RESULT_OK == resultCode && requestCode == 0) {
			Toast.makeText(this, "Value:" + data.getLongExtra("time", 0L), Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("MainActivity", "onResume() chamado");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("MainActivity", "onRestart() chamado");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.i("MainActivity", "onPause() chamado");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.i("MainActivity", "onStart() chamado");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i("MainActivity", "onStop() chamado");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("MainActivity", "onDestroy() chamado");
	}
}
