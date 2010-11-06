package com.android.soslaunch;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocationListener implements LocationListener {
	
	public void onLocationChanged(Location loc) {
		// TODO Auto-generated method stub
		loc.getLatitude();
		loc.getLongitude();
	}
		
	private Context getApplicationContext() {
		// TODO Auto-generated method stub
		return getApplicationContext();
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "GPS disabled", Toast.LENGTH_SHORT).show();
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "GPS Enabled", Toast.LENGTH_SHORT).show();
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
