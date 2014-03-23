package br.edu.fa7.projeto11;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends Activity implements LocationListener {
	
	
	private GoogleMap map;
	private LocationManager locationManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
		
		map = mapFragment.getMap();
		map.setMyLocationEnabled(true);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 5, this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}
	
	@Override
	public void onLocationChanged(Location arg0) {
		LatLng position = new LatLng(arg0.getLatitude(), arg0.getLongitude());
		
		CameraPosition cPos = new CameraPosition(position, 15, 0, 0);
		CameraUpdate cam = CameraUpdateFactory.newCameraPosition(cPos);
		map.animateCamera(cam);
	}

	@Override
	public void onProviderDisabled(String arg0) {
		Log.i("ProviderDisabled", arg0);
	}

	@Override
	public void onProviderEnabled(String arg0) {
		Log.i("onProviderEnabled", arg0);		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		Log.i("onStatusChanged", arg0);
	}
	
}
