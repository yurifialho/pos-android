package br.edu.fa7.projeto06;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class StartedService extends Service{
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Random random = new Random();
				for(int i = 0; i < 20; i++) {
					try {
						
						//Toast.makeText(context, "Numero geradors: " + random.nextInt(100), (int)TimeUnit.SECONDS.toMillis(1)).show();
						Log.i("Service", "Numero geradors: " + random.nextInt(100));
						
						TimeUnit.SECONDS.sleep(2);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				stopSelf();
			}
		}).start();
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
;