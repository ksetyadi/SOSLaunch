package com.android.soslaunch;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SOSLaunch extends Activity
{
	public String latitude;
	public String longitude;
	
	private OnClickListener btnSendListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			// Get the variable first before post the message
			
			// 1. Phone number
			TextView phoneNumber = (TextView)findViewById(R.string.eTxtPhoneNumber);
			String phone = phoneNumber.getText().toString();
			
			// IP Address
			String ipAddr = getLocalIpAddress();
			
			// 2. LatLong
	        LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	        LocationListener mlocListener = new MyLocationListener();
	        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);

	        Location location = mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	        
			Double dLatitude = location.getLatitude();
			Double dLongitude = location.getLongitude();
	        
			String latitude = dLatitude.toString();
			String longitude = dLongitude.toString();
			
	        // String latitude = "-6.194223";
			// String longitude = "106.847694";
			
			// 3. Message
			TextView message = (TextView)findViewById(R.string.eTxtCondition);
			String msg = message.getText().toString();			
			
			// send to crisis central
			int sosSend = sendSOS(phone, ipAddr, latitude, longitude, msg);
			
			Context context = getApplicationContext();
			CharSequence text = "Your SOS has been sent.\n\nThank you.";
			int duration = Toast.LENGTH_LONG;
			
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			finish();
		}
	};
	
	public String getLocalIpAddress() {
	    try
	    {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
	        {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
	            {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress())
	                {
	                    return inetAddress.getHostAddress().toString();
	                }
	            }
	        }
	    } catch (SocketException ex) {
	    	
	    }
	    return null;
	}
	
	public int sendSOS(String ph, String ipAddr, String latitude, String longitude, String msg)
	{
		int statusCode = 0;
		
		HttpClient httpClient = new DefaultHttpClient();
		
		ResponseHandler <String> res = new BasicResponseHandler();
		
		// the URL
		// this is just a sample using my website; you can change it for your own
		HttpPost httpPost = new HttpPost("http://rhok.kristiono-setyadi.net/postit");
		
		try
		{
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
			
			// the Data
			nameValuePairs.add(new BasicNameValuePair("data", "postData"));
			nameValuePairs.add(new BasicNameValuePair("phone_number", ph));
			nameValuePairs.add(new BasicNameValuePair("ipaddress", ipAddr));			
			nameValuePairs.add(new BasicNameValuePair("latitude", latitude));
			nameValuePairs.add(new BasicNameValuePair("longitude", longitude));
			nameValuePairs.add(new BasicNameValuePair("message", msg));
			
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			String response = httpClient.execute(httpPost, res);
		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return statusCode;
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button = (Button)findViewById(R.id.btnSend);
        button.setOnClickListener(btnSendListener);        
    }
}