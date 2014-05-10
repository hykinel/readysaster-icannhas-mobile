package com.icannhas.readysaster;

import android.app.Activity;

public class BaseActivity extends Activity {

	
	// Helper methods
	
	private static final String TRACER = " >> ";
	
	private String getClassName() {
		String full = getClass().getName();
		String last = full.substring(full.lastIndexOf(".") + 1);
		return "[" + last + "]";
	}
	
	protected void logD(String msg) {
		Utilities.logD(getClassName() + TRACER + msg);
	}
	
	protected void logE(String msg) {
		Utilities.logE(getClassName() + TRACER + msg);
	}
	
	protected void logI(String msg) {
		Utilities.logI(getClassName() + TRACER + msg);
	}
}
