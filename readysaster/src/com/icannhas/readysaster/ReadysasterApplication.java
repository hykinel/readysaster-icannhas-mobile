package com.icannhas.readysaster;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.icannhas.readysaster.DaoMaster.DevOpenHelper;
import com.icannhas.readysaster.managers.LocationApiManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

public class ReadysasterApplication extends Application {

	private static ReadysasterApplication singleton;
	private RequestQueue mRequestQueue;
	private DaoSession daoSession;
	
	@Override
	public void onCreate() {
		super.onCreate();

		singleton = this;

		setupUil();
		LocationApiManager.getInstance().startLocationListener(ReadysasterApplication.this);
		DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "readysaster-db", null);
		SQLiteDatabase db = helper.getWritableDatabase();
		DaoMaster daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
		Utilities.logD(">> Finished onCreate on ReadysasterApplication");
	}
	
	public static ReadysasterApplication getInstance() {
		return singleton;
	}
	
	public DaoSession getDaoSession() {
		return daoSession;
	}
	
	private void setupUil() {
		// enable caching
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2).cacheOnDisc(true)
				.considerExifParams(true).build();

		// create global configuration and initialize ImageLoader with this configuration
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).discCacheSize(50 * 1024 * 1024)
				.defaultDisplayImageOptions(defaultOptions).imageDownloader(new BaseImageDownloader(this, 10 * 1000, 40 * 1000)).build();
		ImageLoader.getInstance().init(config);
	}

	/**
	 * @return The Volley Request queue, the queue will be created if it is null
	 */
	public RequestQueue getRequestQueue() {
		// lazy initialize the request queue, the queue instance will be
		// created when it is accessed for the first time
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}

	/**
	 * Adds the specified request to the global queue, if tag is specified then
	 * it is used else Default TAG is used.
	 * 
	 * @param req
	 * @param tag
	 */
	public <T> void addToRequestQueue(Request<T> req, String tag) {
		// set the default tag if tag is empty
		req.setTag(TextUtils.isEmpty(tag) ? Utilities.DEBUG_TAG : tag);

		VolleyLog.d("Adding request to queue: %s", req.getUrl());

		getRequestQueue().add(req);
	}

	/**
	 * Adds the specified request to the global queue using the Default TAG.
	 * 
	 * @param req
	 * @param tag
	 */
	public <T> void addToRequestQueue(Request<T> req) {
		// set the default tag if tag is empty
		req.setTag(Utilities.DEBUG_TAG);

		getRequestQueue().add(req);
	}

	/**
	 * Cancels all pending requests by the specified TAG, it is important to
	 * specify a TAG so that the pending/ongoing requests can be cancelled.
	 * 
	 * @param tag
	 */
	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
}
