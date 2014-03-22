package br.edu.fa7.projeto01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Activity2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity2);
		
		TextView tvTexto = (TextView) findViewById(R.id.tvTexto);
		
		if(getIntent() != null) {
			Intent it = getIntent();
			if(!it.getStringExtra("texto").isEmpty()) {
				tvTexto.setText(it.getStringExtra("texto"));
			}
		}
		/*
		try {
			Thread.currentThread().sleep(7000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		*/
		Intent back = new Intent();
			back.putExtra("time", System.currentTimeMillis());
		setResult(RESULT_OK, back);
		
		//finish();
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("Activity2", "onResume() chamado");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("Activity2", "onRestart() chamado");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.i("Activity2", "onPause() chamado");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.i("Activity2", "onStart() chamado");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i("Activity2", "onStop() chamado");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("Activity2", "onDestroy() chamado");
	}
	
}
