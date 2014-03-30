package br.edu.fa7.projetofinal;

import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

@SuppressLint("HandlerLeak")
public class SplashActivity extends Activity {
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Intent intent = new Intent(SplashActivity.this, ListaComprasActivity.class);
			startActivity(intent);
			
			finish();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		this.handler.sendEmptyMessageDelayed(0, TimeUnit.SECONDS.toMillis(5));
		
		getActionBar().hide();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
