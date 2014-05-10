package com.icannhas.readysaster;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		logD("Hello World!");
	}

}
