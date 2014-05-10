package com.icannhas.readysaster.managers;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.net.http.AndroidHttpClient;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.icannhas.readysaster.ReadysasterApplication;
import com.icannhas.readysaster.ReadysasterSettings;
import com.icannhas.readysaster.Utilities;

public class ApiManager extends BaseManager {
	private static ApiManager singleton;

	public static ApiManager getInstance() {
		if (singleton == null) {
			singleton = new ApiManager();
		}
		return singleton;
	}

	private static String createApiUrl(String endPoint) {
		if (endPoint.startsWith("/"))
			return ReadysasterSettings.API_BASE_URL + endPoint.substring(1);
		return ReadysasterSettings.API_BASE_URL + endPoint;
	}

	private String createApiUrl(String endPoint, Map<String, List<String>> params) {
		StringBuilder sb = new StringBuilder(createApiUrl(endPoint));
		boolean first = true;
		for (String key : params.keySet()) {
			for (String val : params.get(key)) {
				if (first) {
					sb.append("?");
					first = false;
				} else {
					sb.append("&");
				}
				sb.append(key + "=" + val);
			}
		}
		return sb.toString();
	}

	private void printErrorBody(VolleyError error) {
		try {
			String errorBody = new String(error.networkResponse.data, "utf-8");
			Utilities.logE("[Error] >> " + errorBody);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private void newGetRequest(String endPoint, final Map<String, String> headers, final Map<String, List<String>> params, int timeOutMs,
			int retries, int backOffMultiplier, final ApiCallback<String> callback) {
		RequestQueue rq = ReadysasterApplication.getInstance().getRequestQueue();

		StringRequest request = new StringRequest(Request.Method.GET, createApiUrl(endPoint, params), new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				callback.onFetchFromRemote(response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				printErrorBody(error);
				callback.onError(error);
			}
		}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if (headers == null)
					return new HashMap<String, String>();
				return headers;
			}
		};

		request.setRetryPolicy(new DefaultRetryPolicy(timeOutMs, retries, backOffMultiplier));
		rq.add(request);
	}

	private void newPostRequest(String endPoint, final Map<String, String> headers, final Map<String, String> params, int timeOutMs, int retries,
			int backOffMultiplier, final ApiCallback<String> callback) {
		RequestQueue rq = ReadysasterApplication.getInstance().getRequestQueue();

		StringRequest request = new StringRequest(Request.Method.POST, createApiUrl(endPoint), new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				callback.onFetchFromRemote(response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				printErrorBody(error);
				callback.onError(error);
			}
		}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if (headers == null)
					return new HashMap<String, String>();
				return headers;
			}

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				if (params == null)
					return new HashMap<String, String>();
				return params;
			}
		};
		request.setRetryPolicy(new DefaultRetryPolicy(timeOutMs, retries, backOffMultiplier));
		rq.add(request);
	}

	private void newDeleteRequest(String endPoint, final Map<String, String> headers, final Map<String, String> params, int timeOutMs, int retries,
			int backOffMultiplier, final ApiCallback<String> callback) {
		RequestQueue rq = ReadysasterApplication.getInstance().getRequestQueue();
		StringRequest request = new StringRequest(Request.Method.DELETE, createApiUrl(endPoint), new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				callback.onFetchFromRemote(response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				printErrorBody(error);
				callback.onError(error);
			}
		}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if (headers == null)
					return new HashMap<String, String>();
				return headers;
			}

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				if (params == null)
					return new HashMap<String, String>();
				return params;
			}
		};

		request.setRetryPolicy(new DefaultRetryPolicy(timeOutMs, retries, backOffMultiplier));
		rq.add(request);
	}

	private void newPatchRequest(String endPoint, final Map<String, String> headers, final Map<String, String> params, int timeOutMs, int retries,
			int backOffMultiplier, final ApiCallback<String> callback) {
		RequestQueue rq = ReadysasterApplication.getInstance().getRequestQueue();
		String userAgent = "volley/0";
		HttpStack httpStack = new HttpClientStack(AndroidHttpClient.newInstance(userAgent));
		rq = Volley.newRequestQueue(ReadysasterApplication.getInstance(), httpStack);

		StringRequest request = new StringRequest(Request.Method.PATCH, createApiUrl(endPoint), new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				callback.onFetchFromRemote(response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				printErrorBody(error);
				callback.onError(error);
			}
		}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if (headers == null)
					return new HashMap<String, String>();
				return headers;
			}

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				if (params == null)
					return new HashMap<String, String>();
				return params;
			}
		};

		request.setRetryPolicy(new DefaultRetryPolicy(timeOutMs, retries, backOffMultiplier));
		rq.add(request);
	}

	public static abstract class ApiCallback<T> {

		protected abstract void onFetchFromDb(T response);

		protected abstract void onFetchFromRemote(T response);

		protected abstract void onError(VolleyError error);
	}

	public static class SimpleFetchCallback<T> extends ApiCallback<T> {
		@Override
		protected void onFetchFromDb(T response) {}

		@Override
		protected void onFetchFromRemote(T response) {}

		@Override
		protected void onError(VolleyError error) {}
	}

}
