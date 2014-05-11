package com.icannhas.readysaster;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DataListFragment extends Fragment {
	
	private ListView vList;
	private List<Data> data;
	private List<String> names;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootview = inflater.inflate(R.layout.fragment_data_list, null);
		vList = (ListView) rootview.findViewById(R.id.fragment_listviw);
		data = ReadysasterApplication.getInstance().getDaoSession().getDataDao().queryBuilder().list();
		names = new ArrayList<String>();
		for(int i = 0;i<data.size();i++)
			names.add(data.get(i).getPersonalDetails().getName());
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_row, R.id.list_name, names);
		vList.setAdapter(adapter);
		setupListeners();
		return rootview;
	}
	
	public void setupListeners(){
		vList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				Intent intent = new Intent(getActivity(), DetailsActivity.class);
				intent.putExtra(Utilities.INTENT_KEY_DETAILSACTIVITY, position);
				startActivity(intent);
				
			}
		});
	}

}
