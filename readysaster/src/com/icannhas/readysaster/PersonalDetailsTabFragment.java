package com.icannhas.readysaster;

import java.util.ArrayList;

import android.text.InputType;

import com.icannhas.readysaster.utils.views.DetailsRow;

public class PersonalDetailsTabFragment extends BasicTabFragment {
	
	private DetailsRow mNameRow;
	private DetailsRow mNumberRow;

	@Override
	public int getLayoutResourceId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_tab_1;
	}

	@Override
	public void setupViews() {
		// TODO Auto-generated method stub
		mNameRow = (DetailsRow) findViewById(R.id.detailsrow_name);
		mNameRow.setTitle("Name");
		mNameRow.hasChoices(false);
		
		mNumberRow = (DetailsRow) findViewById(R.id.detailsrow_number);
		mNumberRow.setTitle("Contact Number");
		mNumberRow.hasChoices(false);
		mNumberRow.setInputType(InputType.TYPE_CLASS_NUMBER);
		
		mRowList = new ArrayList<DetailsRow>();
		//add the rows to list
		mRowList.add(mNameRow);
		mRowList.add(mNumberRow);
		
	}
	
	public PersonalDetails getPersonalDetails(){
		return new PersonalDetails(null, mNameRow.getDetails(), Long.parseLong(mNumberRow.getDetails()));
		
	}
	
	@Override
	public void setupListeners() {
		// TODO Auto-generated method stub
		
	}
	
}
