package com.icannhas.readysaster;

import android.util.Log;

public class Utilities {

	public static final String DEBUG_TAG = "READYSASTER_DEBUG";
	public static final String ERROR_TAG = "READYSASTER_ERROR";
	public static final String INFO_TAG = "READYSASTER_INFO";
	
	public static final String INTENT_KEY_DETAILSACTIVITY = "INTENT_KEY_DETAILSACTIVITY";
	
	// Data choices
	public static final String[] buildingMaterial = new String[] { "WOOD", "HYBRID", "MASONRY", "CONCRETE", "STEEL" };
	public static String[] getStructureTypeList(String buildingMat) {
		if (buildingMat.equals("WOOD")) {
			return new String[] { "Wood, light frame", "Bamboo" };
		}
		if (buildingMat.equals("HYBRID")) {
			return new String[] { "Makeshift" };
		}
		if (buildingMat.equals("MASONRY")) {
			return new String[] { "Concrete hollow blocks with wood or light metal", "Concrete hollow blocks", "Adobe",
					"Unreinforced masonry bearing walls" };
		}
		if (buildingMat.equals("CONCRETE")) {
			return new String[] { "Reinforced concrete moment frames with wood or light metal", "Reinforced concrete moment frames",
					"Concrete shear walls and frames", "Precast Frame" };
		} else {
			return new String[] { "Steel moment frame", "Light metal frame", "Steel frame with cast-In-place concrete shear walls" };
		}
	}

	// Request Code 
	public static final int REQUEST_APP_CAMERA = 1001;

	// Helper methods
	public static void logD(String msg) {
		if (ReadysasterSettings.DEBUG)
			Log.d(DEBUG_TAG, msg);
	}

	public static void logE(String msg) {
		if (ReadysasterSettings.DEBUG)
			Log.e(ERROR_TAG, msg);
	}

	public static void logI(String msg) {
		if (ReadysasterSettings.DEBUG)
			Log.i(INFO_TAG, msg);
	}
}
