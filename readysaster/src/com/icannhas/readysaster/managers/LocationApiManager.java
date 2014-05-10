package com.icannhas.readysaster.managers;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.icannhas.readysaster.ReadysasterSettings;

public class LocationApiManager extends BaseManager {

	private static LocationApiManager singleton;

	public static LocationApiManager getInstance() {
		if (singleton == null) {
			singleton = new LocationApiManager();
		}
		return singleton;
	}

	private Location currentLocation;
	private LocationManager locationManager;
	private CustomLocationListener mListener;

	private Geocoder mGeoCoder;

	public void getLocationName() {
		if (mGeoCoder != null && Geocoder.isPresent() && currentLocation != null) {
			try {
				List<Address> addresses = mGeoCoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
				if (addresses != null && addresses.size() > 0) {
					Address address = addresses.get(0);

					if (mListener != null) {
						String locationName = address.getLocality() + ", " + address.getAdminArea();
						mListener.onLocationNameTaken(locationName);
						return;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		mListener.onLocationNameTaken("----, ----");
	}
	
	public Location getCurrentLocation() {
		logD("Printing current location");
		return currentLocation;
	}

	public void setCurrentLocation(Location location) {
		logD("Setting current location");
		currentLocation = location;
	}

	private LocationListener mLocationListener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {}

		@Override
		public void onProviderEnabled(String provider) {}

		@Override
		public void onProviderDisabled(String provider) {}

		@Override
		public void onLocationChanged(Location location) {
			logD("Got location " + location.getLatitude() + " " + location.getLongitude());
			LocationApiManager.getInstance().setCurrentLocation(location);
			if (mListener != null) {
				mListener.onLocationChanged(location);
				getLocationName();
			}
		}
	};

	public void startLocationListener(Context context) {
		if (Geocoder.isPresent())
			mGeoCoder = new Geocoder(context);

		logD("Started Listening");
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		String provider = locationManager.getBestProvider(criteria, true);
		logD("<< Provider: " + provider);

		logD("Setting initial location");
		currentLocation = locationManager.getLastKnownLocation(provider);
		if (currentLocation == null) {
			currentLocation = new Location(provider);
			currentLocation.setLatitude(ReadysasterSettings.DEFAULT_LATITUDE);
			currentLocation.setLongitude(ReadysasterSettings.DEFAULT_LONGITUDE);
		}
		logD("Initial location: " + currentLocation);

		if (provider != null) {
			locationManager.requestLocationUpdates(provider, ReadysasterSettings.LOCATION_UPDATE_INTERVAL, ReadysasterSettings.LOCATION_UPDATE_DISTANCE,
					mLocationListener);
		}

	}

	public void resetLocationListener(Context context) {
		locationManager.removeUpdates(mLocationListener);
		startLocationListener(context);
	}

	public void setLocationListener(CustomLocationListener listener) {
		mListener = listener;
	}

	public interface CustomLocationListener {

		public void onLocationChanged(Location location);

		public void onLocationNameTaken(String locationName);
	}
}
