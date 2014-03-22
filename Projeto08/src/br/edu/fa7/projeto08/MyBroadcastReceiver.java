package br.edu.fa7.projeto08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Toast.makeText(context, "Alarme Executado", Toast.LENGTH_SHORT).show();
		Log.i("Alarme", "Alarme Executado");
	}

}
