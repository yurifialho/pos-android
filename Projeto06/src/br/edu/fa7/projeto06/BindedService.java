package br.edu.fa7.projeto06;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BindedService extends Service {
	
	private IBinder binder;
	private Random random;
	
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.binder = new LocalBinder();
		this.random = new Random();
	}
	
	class LocalBinder extends Binder {
		public BindedService getService() {
			return BindedService.this;
		}
	}
	
	public int getRandomValue() {
		return random.nextInt(100);
	}
	
	public void stopService() {
		this.stopSelf();
	}
	
	
}
