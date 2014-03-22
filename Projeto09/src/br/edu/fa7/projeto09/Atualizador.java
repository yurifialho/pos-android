package br.edu.fa7.projeto09;

import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class Atualizador implements Runnable{
	
	private Handler h;
	
	public Atualizador(Handler h) {
		this.h = h;
	}
	
	@Override
	public void run() {
		for(int i =0; i < 20; i++) {
			//tvContador.setText(i);
			Bundle b = new Bundle();
				b.putInt("numero", i);
				
				Message msg = new Message();
				msg.what = 1;
				msg.setData(b);
				
				h.sendMessage(msg);
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
