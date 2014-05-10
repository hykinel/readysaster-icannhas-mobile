package com.icannhas.readysaster.utils.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.icannhas.readysaster.R;

public class DetailsRow extends LinearLayout{
	
	private TextView vDetailsTitle;
	private EditText vDetailsDetails;
	private Spinner vDetailsSpinner;
	private Context mContext;
	
	private boolean hasChoice = false;
	
	public DetailsRow(Context context) {
		super(context);
		init(context);
	}

	public DetailsRow(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public DetailsRow(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_details_row, this, true);
		mContext = context;
		vDetailsTitle = (TextView)findViewById(R.id.details_title);
		vDetailsDetails = (EditText)findViewById(R.id.details_edit_text);
		vDetailsSpinner = (Spinner)findViewById(R.id.details_spinner);
		
	}
	
	public void setTitle(String title){
		vDetailsTitle.setText(title);
	}
	
	public void hasChoices(boolean boo){
		hasChoice = boo;
		if(boo){
			vDetailsSpinner.setVisibility(View.VISIBLE);
			vDetailsDetails.setVisibility(View.GONE);
		}
		else{
			vDetailsSpinner.setVisibility(View.GONE);
			vDetailsDetails.setVisibility(View.VISIBLE);
		}
	}
	
	public String getTitle(){
		return vDetailsTitle.getText().toString();
	}
	
	public String getDetails(){
		if(hasChoice)
			return vDetailsSpinner.getItemAtPosition(vDetailsSpinner.getSelectedItemPosition()).toString();
		return vDetailsDetails.getText().toString();
	}
	
	public void setInputType(int type){
		if(!hasChoice)
			vDetailsDetails.setRawInputType(type);
	}
	
	public void populateSpinner(String[] list){
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
		        android.R.layout.simple_spinner_item, list);
		vDetailsSpinner.setAdapter(adapter);
	}
	
	public void setSpinnerSelectedListener(OnItemSelectedListener listener){
		vDetailsSpinner.setOnItemSelectedListener(listener);
	}
	
}
