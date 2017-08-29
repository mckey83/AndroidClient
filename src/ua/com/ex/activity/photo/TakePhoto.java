package ua.com.ex.activity.photo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import ua.com.ex.activity.MainActivity;

public class TakePhoto {
	
	private MainActivity activity;	
	static final int REQUEST_TAKE_PHOTO = 1;
	
	
	public TakePhoto(MainActivity activity) {
		super();
		this.activity = activity;
		
	}

	public void dispatchTakePictureIntent() throws Exception{
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);		
		takePictureIntent.resolveActivity(activity.getPackageManager());
		File photoFile = createImageFile();				
		Uri photoURI = FileProvider.getUriForFile(activity,"ua.com.ex",photoFile);
		takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
		activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
	}
	
	private File createImageFile() throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(imageFileName,".jpg",storageDir);
		activity.setCurrentPhotoPath(image.getAbsolutePath());
		return image;
	}

}
