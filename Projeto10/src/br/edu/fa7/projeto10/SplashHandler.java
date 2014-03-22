package br.edu.fa7.projeto10;

import java.util.concurrent.TimeUnit;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SplashHandler implements Runnable {
	
	private Handler handler;
	
	public SplashHandler(Handler handler) {
		this.handler = handler;
	}
	
	@Override
	public void run() {
		Message msg = new Message();
		try {
			msg.what = 0;
			TimeUnit.SECONDS.sleep(2);
		} catch(Exception e) {
			Log.e("SplashHandle", e.getMessage());
			msg.what = 1;
		}
		handler.sendMessage(msg);
	}

}
