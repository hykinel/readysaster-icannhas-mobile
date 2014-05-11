package com.icannhas.readysaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DetailsActivity extends ListActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		Intent intent = getIntent();
		int position = intent.getIntExtra(Utilities.INTENT_KEY_DETAILSACTIVITY, -1);
		
		List<Data> dataList = ReadysasterApplication.getInstance().getDaoSession().getDataDao().queryBuilder().list();
		Data data = dataList.get(position);

		List<HashMap<String, String>> listMap = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "Name");
		map.put("val", data.getPersonalDetails().getName());
		listMap.add(map);
		map = new HashMap<String, String>();
		map.put("key", "Contact Number");
		map.put("val", "" + data.getPersonalDetails().getContact_number());
		// map.put("contact number",
		// ""+data.getPersonalDetails().getContact_number());
		listMap.add(map);
		map = new HashMap<String, String>();
		map.put("key", "Latitude");
		map.put("val", "" + data.getLocationDetails().getLat());
		// map.put("latitude", ""+data.getLocationDetails().getLat());
		listMap.add(map);
		map = new HashMap<String, String>();
		map.put("key", "Longitude");
		map.put("val", "" + data.getLocationDetails().getLng());
		// map.put("longitude", ""+data.getLocationDetails().getLng());
		listMap.add(map);
		map = new HashMap<String, String>();
		map.put("key", "Photo Location");
		map.put("val", data.getLocationDetails().getPhotoFile());
		// map.put("photo location", data.getLocationDetails().getPhotoFile());
		listMap.add(map);
		map = new HashMap<String, String>();
		map.put("key", "House Size");
		map.put("val", "" + data.getStructureDetails().getHouse_size());
		// map.put("house size", ""+data.getStructureDetails().getHouse_size());
		listMap.add(map);
		map = new HashMap<String, String>();
		map.put("key", "Building Material");
		map.put("val", data.getStructureDetails().getBuilding_material());
		// map.put("building material",
		// data.getStructureDetails().getBuilding_material());
		listMap.add(map);
		map = new HashMap<String, String>();
		map.put("key", "Structure Type");
		map.put("val", data.getStructureDetails().getStructure_type());
		// map.put("structure type",
		// data.getStructureDetails().getStructure_type());
		listMap.add(map);
		map = new HashMap<String, String>();
		map.put("key", "Number of Storeys");
		map.put("val", "" + data.getStructureDetails().getNumber_storey());
		// map.put("number of storeys",
		// ""+data.getStructureDetails().getNumber_storey());
		listMap.add(map);

		ListAdapter adapter = new SimpleAdapter(this, listMap, R.layout.list_details, new String[] { "key", "val" }, new int[] { R.id.textview_key,
				R.id.textview_val });

		setListAdapter(adapter);

	}

}
