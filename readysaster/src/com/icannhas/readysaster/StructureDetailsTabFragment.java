package com.icannhas.readysaster;

import java.util.ArrayList;

import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.icannhas.readysaster.utils.views.DetailsRow;
import com.icannhas.readysaster.utils.views.Utilities;

public class StructureDetailsTabFragment extends BasicTabFragment {
	
	private DetailsRow mHouseSizeRow;
	private DetailsRow mBuildingMaterialRow;
	private DetailsRow mStructuralTypeRow;
	private DetailsRow mNumberStoreysRow;
	
	@Override
	public void setupViews() {
		// TODO Auto-generated method stub
		mHouseSizeRow = (DetailsRow) findViewById(R.id.detailsrow_house_size);
		mHouseSizeRow.setTitle("House Size");
		mHouseSizeRow.hasChoices(false);
		mHouseSizeRow.setInputType(InputType.TYPE_CLASS_NUMBER);
		
		mBuildingMaterialRow = (DetailsRow) findViewById(R.id.detailsrow_building_material);
		mBuildingMaterialRow.setTitle("Building Material");
		mBuildingMaterialRow.hasChoices(true);
		mBuildingMaterialRow.populateSpinner(Utilities.buildingMaterial);

		mStructuralTypeRow = (DetailsRow) findViewById(R.id.detailsrow_structure_type);
		mStructuralTypeRow.setTitle("Structural Type");
		mStructuralTypeRow.hasChoices(true);
		mStructuralTypeRow.populateSpinner(Utilities.getStructureTypeList(""));

		mNumberStoreysRow = (DetailsRow) findViewById(R.id.detailsrow_number_storey);
		mNumberStoreysRow.setTitle("Number of Storey");
		mNumberStoreysRow.hasChoices(false);
		mNumberStoreysRow.setInputType(InputType.TYPE_CLASS_NUMBER);

		mRowList = new ArrayList<DetailsRow>();
		// add the rows to list
		mRowList.add(mHouseSizeRow);
		mRowList.add(mBuildingMaterialRow);
		mRowList.add(mStructuralTypeRow);
		mRowList.add(mNumberStoreysRow);

	}
	
	@Override
	public void setupListeners() {
		OnItemSelectedListener buildingMatsListener = new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
				// TODO Auto-generated method stub
				mStructuralTypeRow.populateSpinner(Utilities.getStructureTypeList(Utilities.buildingMaterial[position]));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		};

		mBuildingMaterialRow.setSpinnerSelectedListener(buildingMatsListener);
	}
	
	public Structure getStructureDetails(){
		return new Structure(null, Integer.parseInt(mHouseSizeRow.getDetails()), mBuildingMaterialRow.getDetails(), mStructuralTypeRow.getDetails(), Integer.parseInt(mNumberStoreysRow.getDetails()));
	}
	
	@Override
	public int getLayoutResourceId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_tab_3;
	}

	
}
