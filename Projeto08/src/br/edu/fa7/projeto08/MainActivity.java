package br.edu.fa7.projeto08;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button alSemRepeticao;
	private Button alComRepeticao;
	
	private Button btCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		alSemRepeticao = (Button) findViewById(R.id.button1);
		alSemRepeticao.setOnClickListener(this);
		
		alComRepeticao = (Button) findViewById(R.id.button2);
		alComRepeticao.setOnClickListener(this);
		
		btCancel = (Button) findViewById(R.id.button3);
		btCancel.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.SECOND, 5);
		
		Intent i = new Intent("EXECUTAR_ALARME");
		
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
		
		AlarmManager alm = (AlarmManager) getSystemService(ALARM_SERVICE);
		
		alm.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi); 
		switch (view.getId()) {
		case R.id.button1:
			alm.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi); 
			
			break;
		case R.id.button2:
			alm.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 5000, pi); 
			break;
		case R.id.button3:
			alm.cancel(pi);
			break;
		default:
			break;
		}
	}

}
