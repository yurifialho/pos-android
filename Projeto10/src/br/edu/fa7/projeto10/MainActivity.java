package br.edu.fa7.projeto10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private static final int SPLAH_KEY = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getHandler().sendEmptyMessageDelayed(SPLAH_KEY, 3000);
		getActionBar().hide();
		//new Thread(new SplashHandler(getHandler())).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private Handler getHandler() {
		Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				toExtrato();
				finish();
			}
		};
		return handler;
	}
	
	public void toExtrato() {
		Intent i = new Intent(this, ExtratoActivity.class);
		startActivity(i);
	}
}
