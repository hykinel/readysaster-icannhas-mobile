package com.icannhas.readysaster;

import java.util.ArrayList;
import java.util.List;

import com.icannhas.readysaster.utils.views.DetailsRow;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BasicTabFragment extends Fragment {

	private View vSelf;
	protected List<DetailsRow> mRowList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		vSelf = inflater.inflate(getLayoutResourceId(), null);
		
		mRowList = new ArrayList<DetailsRow>();
		setupViews();
		setupListeners();

		return vSelf;
	}

	protected View findViewById(int resourceId) {
		return vSelf.findViewById(resourceId);
	}
	
	public String toJsonString(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<mRowList.size();i++){
			DetailsRow row = mRowList.get(i);
			sb.append("{\n");
			sb.append("title = ");
			sb.append(row.getTitle());
			sb.append(",\n");
			sb.append("details = ");
			sb.append(row.getDetails());
			sb.append("\n}");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	public abstract int getLayoutResourceId();

	public abstract void setupViews();

	public abstract void setupListeners();

}
