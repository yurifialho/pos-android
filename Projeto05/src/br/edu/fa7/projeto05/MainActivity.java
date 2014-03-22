package br.edu.fa7.projeto05;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button button1;
	private Button button2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.button1 = (Button) findViewById(R.id.button1);
		this.button1.setOnClickListener(this);
		
		this.button2 = (Button) findViewById(R.id.button2);
		this.button2.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		Notification.Builder builder = null;
		Intent resultIntent = null;
		TaskStackBuilder taskStackBuilder = null;
		PendingIntent resultPendingIntent = null;
		NotificationManager notificationManager = null;
		
		switch (v.getId()) {
		case R.id.button1:
			builder = new Notification.Builder(this);
			builder.setContentTitle("Minha notificacao!");
			builder.setContentText("Esta e a primeira notificacao");
			builder.setSmallIcon(R.drawable.ic_launcher);
			
			resultIntent = new Intent(this, CustomActivity.class);
			
			taskStackBuilder = TaskStackBuilder.create(this);
			taskStackBuilder.addNextIntent(resultIntent);
			
			resultPendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
			
			builder.setContentIntent(resultPendingIntent);
			
			notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			notificationManager.notify(1, builder.build());
			break;
		case R.id.button2:
			builder = new Notification.Builder(this);
			builder.setSmallIcon(R.drawable.ic_launcher);
			
			resultIntent = new Intent(this, CustomActivity.class);
			
			taskStackBuilder = TaskStackBuilder.create(this);
			taskStackBuilder.addNextIntent(resultIntent);
			
			resultPendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
			
			builder.setContentIntent(resultPendingIntent);
			
			Notification.InboxStyle style = new Notification.InboxStyle();
				style.setBigContentTitle("Minha notificacao");
				for(int i =0; i <= 20; i++) {
					style.addLine("Linha: " + i);
				}
				
			builder.setStyle(style);
			notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			notificationManager.notify(2, builder.build());
			break;
		default:
			break;
		}
	}

}