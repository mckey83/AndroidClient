package ua.com.ex.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import ua.com.ex.R;
import ua.com.ex.activity.photo.TakePhoto;
import ua.com.ex.models.Category;
import ua.com.ex.models.Settings;


public class MainActivity extends Activity {
	
	private String currentPhotoPath;
	private TakePhoto takePhoto = new TakePhoto(this);
	static final int REQUEST_TAKE_PHOTO = 1;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		new Settings();
		setButtonCatalog();
		setButtonPhoto();
	}

	private void setButtonCatalog() {
		final Intent intentCategoryList = new Intent(this, CategoryListActivity.class);		
		OnClickListener onClickListenerButtonCategoryListActivity = new OnClickListener() {			
			public void onClick(View v) {	
				Category categoryRoot = new Category();
				categoryRoot.setId(1);
				intentCategoryList.putExtra("parentCategory", categoryRoot);
				startActivity(intentCategoryList);	
			}
		};			
		((Button) findViewById(R.id.buttonCatalog)).setOnClickListener(onClickListenerButtonCategoryListActivity);
	}

	private void setButtonPhoto() {
		OnClickListener onClickListenerButtonIpAddress = new OnClickListener() {
			public void onClick(View v) {	
				try {
					takePhoto.dispatchTakePictureIntent();
				} catch (Exception ex) {
					Log.d("MyTAG", "dispatchTakePictureIntent()" +ex.getMessage());
				}
			}
		};			
		((Button) findViewById(R.id.photo)).setOnClickListener(onClickListenerButtonIpAddress);
	}	

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {		
			if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {			
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inPreferredConfig = Bitmap.Config.ARGB_8888;
				Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, options);
				((ImageView)findViewById(R.id.image)).setImageBitmap(bitmap);
			}
		} catch (Exception e) {
			Log.d("MyTAG", "onActivityResult " +e.getMessage());
		}
	}	
	
	public void setCurrentPhotoPath(String path){
		currentPhotoPath = path; 
	}

	
}
