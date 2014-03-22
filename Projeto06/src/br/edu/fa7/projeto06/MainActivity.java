package br.edu.fa7.projeto06;

import br.edu.fa7.projeto06.BindedService.LocalBinder;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button startService;
	private Button bindService;
	private Button recuperarValor;
	
	private BindedService bindedService;
	private boolean isBinded;
	
	private ProgressBar pro;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startService = (Button) findViewById(R.id.bt1);
			startService.setOnClickListener(this);
		bindService  = (Button) findViewById(R.id.bt2);
			bindService.setOnClickListener(this);
		recuperarValor = (Button) findViewById(R.id.button1);
			recuperarValor.setOnClickListener(this);
			recuperarValor.setEnabled(false);
		pro = (ProgressBar) findViewById(R.id.progressBar1);
		
		isBinded = false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		Intent i = null; 
		switch (view.getId()) {
		case R.id.bt1:
			pro.setVisibility(ProgressBar.VISIBLE);
			i = new Intent(this, StartedService.class);
			startService(i);
			pro.setVisibility(ProgressBar.INVISIBLE);
			break;
		case R.id.bt2:
			pro.setVisibility(ProgressBar.VISIBLE);
			if(!isBinded) {
				i = new Intent(this, BindedService.class);
				bindService(i, new CustomServiceConnection(), Context.BIND_AUTO_CREATE);
				pro.setVisibility(ProgressBar.INVISIBLE);
				recuperarValor.setEnabled(true);
			} else {
				pro.setVisibility(ProgressBar.INVISIBLE);
				Toast.makeText(this, "Service ainda esta executando", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.button1:
			pro.setVisibility(ProgressBar.VISIBLE);
			Toast.makeText(this, "O Valor e " + bindedService.getRandomValue(), Toast.LENGTH_SHORT).show();
			bindedService.stopService();
			pro.setVisibility(ProgressBar.INVISIBLE);
			break;
		default:
			break;
		}
	}
	
	class CustomServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			LocalBinder binder = (LocalBinder) service;
			bindedService = binder.getService();
			isBinded = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			isBinded = false;
			recuperarValor.setEnabled(false);
		}
		
	}

}
