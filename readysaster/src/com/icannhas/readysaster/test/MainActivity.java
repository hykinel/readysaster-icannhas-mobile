package com.icannhas.readysaster.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.icannhas.readysaster.BaseActivity;
import com.icannhas.readysaster.R;
import com.icannhas.readysaster.Utilities;
import com.icannhas.readysaster.managers.LocationApiManager;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends BaseActivity {

	private Button vCamera;
	private ImageView vThumbnail;
	private ImageView vFullPhoto;

	private File mPhotoFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		logD("Hello World!");
		Location location = LocationApiManager.getInstance().getCurrentLocation();
		logD("Current coords: " + location);

		setupViews();
		setupListeners();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Utilities.REQUEST_APP_CAMERA && resultCode == RESULT_OK) {
			logD("Data received: " + data);
		    Uri uri = Uri.fromFile(mPhotoFile);
		    ImageLoader.getInstance().displayImage(uri.toString(), vFullPhoto);
		    galleryAddPic();
		}
	}

	private void setupViews() {
		vCamera = (Button) findViewById(R.id.button_camera);
		vThumbnail = (ImageView) findViewById(R.id.thumbnail);
		vFullPhoto = (ImageView) findViewById(R.id.full_photo);
	}

	private void setupListeners() {
		vCamera.setOnClickListener(mCameraListener);
	}

	private View.OnClickListener mCameraListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			if (intent.resolveActivity(getPackageManager()) != null) {
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
	    this.sendBroadcast(mediaScanIntent);
	}
}
