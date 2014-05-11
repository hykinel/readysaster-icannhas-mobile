package com.icannhas.readysaster;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.icannhas.readysaster.managers.LocationApiManager;
import com.icannhas.readysaster.utils.views.DetailsRow;
import com.nostra13.universalimageloader.core.ImageLoader;

public class LocationDetailsFragment extends BasicTabFragment {

	private DetailsRow mLatRow;
	private DetailsRow mLngRow;

	private Button vCamera;
	private ImageView vFullPhoto;

	private File mPhotoFile;

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Utilities.REQUEST_APP_CAMERA && resultCode == Activity.RESULT_OK) {
			Utilities.logD("Data received: " + data);
			Uri uri = Uri.fromFile(mPhotoFile);
			ImageLoader.getInstance().displayImage(uri.toString(), vFullPhoto);
			galleryAddPic();
		}
	}

	@Override
	public int getLayoutResourceId() {
		return R.layout.fragment_tab_2;
	}

	@Override
	public void setupViews() {
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

		//photo views
		vCamera = (Button) findViewById(R.id.button_camera);
		vFullPhoto = (ImageView) findViewById(R.id.full_photo);

		populateLocationFields();
	}

	private void populateLocationFields() {
		Location location = LocationApiManager.getInstance().getCurrentLocation();
		if (location != null) {
			EditText latVal = (EditText) mLatRow.findViewById(R.id.details_edit_text);
			latVal.setText(String.format("%.4f", location.getLatitude()));
			EditText lngVal = (EditText) mLngRow.findViewById(R.id.details_edit_text);
			lngVal.setText(String.format("%.4f", location.getLongitude()));
		}
	}

	public LocationDetails getLocationDetails() {
		return new LocationDetails(null, Double.parseDouble(mLatRow.getDetails()), Double.parseDouble(mLngRow.getDetails()), "");
	}

	@Override
	public void setupListeners() {
		vCamera.setOnClickListener(mCameraListener);
	}

	private View.OnClickListener mCameraListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
				mPhotoFile = null;
				try {
					createImageFile();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (mPhotoFile != null) {
					intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPhotoFile));
					startActivityForResult(intent, Utilities.REQUEST_APP_CAMERA);
				}
			}
		}
	};

	// File write helpers

	private void createImageFile() throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "IMG_" + timeStamp + "_";
		File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Readysaster");
		if (!storageDir.exists()) {
			storageDir.mkdirs();
		}
		File image = File.createTempFile(imageFileName, ".jpg", storageDir);
		mPhotoFile = image;
	}

	private void galleryAddPic() {
		Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		String currentPhotoPath = "file:" + mPhotoFile.getAbsolutePath();
		File f = new File(currentPhotoPath);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		getActivity().sendBroadcast(mediaScanIntent);
	}

}
