package com.icannhas.readysaster;

import com.icannhas.readysaster.DaoMaster.DevOpenHelper;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class ReadysasterApplication extends Application {

	private static ReadysasterApplication singleton;
	private DaoSession daoSession;

	@Override
	public void onCreate() {
		super.onCreate();

		singleton = this;

		// setup database
		DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "readysaster-db", null);
		SQLiteDatabase db = helper.getWritableDatabase();
		DaoMaster daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();

	}

	public static ReadysasterApplication getInstance() {
		return singleton;
	}

	public DaoSession getDaoSession() {
		return daoSession;
	}

}
