package com.example.projeto07;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Log.i("Action", intent.getAction()); 
		
		Toast.makeText(context, "Meu primeiro BroadcastReceiver", Toast.LENGTH_SHORT).show();
		Log.i("Teste", "Teste");
	}

}
