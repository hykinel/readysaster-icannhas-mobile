package com.icannhas.readysaster;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BaseActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_save:
			logD("Clicked save");
			return true;
		case R.id.action_settings:
			logD("Clicked settings");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

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
