package com.icannhas.readysaster;

import java.util.ArrayList;

import com.icannhas.readysaster.utils.views.DetailsRow;

public class LocationDetailsFragment extends BasicTabFragment {
	
	private DetailsRow mLatRow;
	private DetailsRow mLngRow;

	@Override
	public int getLayoutResourceId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_tab_2;
	}

	@Override
	public void setupViews() {
		// TODO Auto-generated method stub

		mLatRow = (DetailsRow) findViewById(R.id.detailsrow_lat);
		mLatRow.setTitle("Latitude");
		mLatRow.hasChoices(false);
		
		mLngRow = (DetailsRow) findViewById(R.id.detailsrow_lng);
		mLngRow.setTitle("Longitude");
		mLngRow.hasChoices(false);
		
		mRowList = new ArrayList<DetailsRow>();
		//add the rows to list
		mRowList.add(mLatRow);
		mRowList.add(mLngRow);
	}
	
	public Location getLocationDetails(){
		return new Location(null, Double.parseDouble(mLatRow.getDetails()), Double.parseDouble(mLngRow.getDetails()));
	}
	
	@Override
	public void setupListeners() {
		// TODO Auto-generated method stub
		
	}

}
