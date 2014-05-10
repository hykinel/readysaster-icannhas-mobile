package com.icannhas.readysaster;

import android.location.Location;
import android.os.Bundle;

import com.icannhas.readysaster.managers.LocationApiManager;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		logD("Hello World!");
		Location location = LocationApiManager.getInstance().getCurrentLocation();
		logD("Current coords: " + location);
	}

}
