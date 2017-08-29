package ua.com.ex.activity.category;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import ua.com.ex.R;
import ua.com.ex.activity.CategoryListActivity;
import ua.com.ex.activity.MainActivity;
import ua.com.ex.activity.ProductListActivity;
import ua.com.ex.models.Category;
import ua.com.ex.services.CategoryService;

public class CategoryListMain {

	private static final int LEFT_LAYOUT = 1;
	private static final int RIGHT_LAYOUT = 2;		
	private Activity activity;	
	private Category parentCategory;
	private List<Category> listChildrenCategory = new ArrayList<>();
	private static final CategoryService categoryService = new CategoryService();
	private LinearLayout categoryMainContent;	

	public CategoryListMain(Activity activity,  Category parentCategory ){
		this.activity = activity;
		this.parentCategory = parentCategory;	
		this.categoryMainContent = (LinearLayout) activity.findViewById(R.id.categoryMainContent);
		
	}
	
	public void refresh(){		
		categoryMainContent.removeAllViews();
		show();
	}

	public void show() {	
		listChildrenCategory = getCategoryByParentId(""+parentCategory.getId());
		Log.d("MyTAG", "CategoryListMain  size = "+ listChildrenCategory.size());
		if (!listChildrenCategory.isEmpty()){
			showCategory(listChildrenCategory);
		} else{			
			if (parentCategory.getParentId()!=0){						
				activity.startActivity(createIntentToProduct());			
			}else {		
				Log.e("MyTAG", "CategoryListMain  isEmpty() and id = 0");
				activity.startActivity(new Intent(activity, MainActivity.class));				
			}
		}
	}

	private List<Category> getCategoryByParentId(String parentId) {
		return categoryService.getCategoryByParentId(parentId);
	}

	private void showCategory( List<Category> listChildrenCategory) {			
		int index = LEFT_LAYOUT;
		LinearLayout categoryMainContent = getMainContent();
		View categoryListItem = getCategoryListItem(categoryMainContent);
		LinearLayout row = getRow(categoryListItem);
		for (int i = 0; i < listChildrenCategory.size(); i++) {			
			Category current = listChildrenCategory.get(i);
			Log.d("MyTAG", current.getName());
			if (index == LEFT_LAYOUT){				
				showInLeftLayout(categoryListItem, current);
				if (i == listChildrenCategory.size() -1){
					View view = (View)categoryListItem.findViewById(R.id.rightRow);
					view.setVisibility(View.INVISIBLE);
					categoryMainContent.addView(row);				
				}
			} 
			else if (index == RIGHT_LAYOUT){
				showInRightLayout(categoryListItem, current);
				categoryMainContent.addView(row);
				categoryListItem = getCategoryListItem(categoryMainContent);
				row = getRow(categoryListItem);
				index = 0;
			}			
			index++;			
		}		
	}

	private LinearLayout getMainContent() {
		return (LinearLayout) activity.findViewById(R.id.categoryMainContent);
	}

	private View getCategoryListItem(LinearLayout categoryMainContent) {		
		return activity.getLayoutInflater().inflate(R.layout.category_list_item, categoryMainContent, false);
	}

	private LinearLayout getRow(View categoryListItem) {
		return (LinearLayout)categoryListItem.findViewById(R.id.row);
	}

	private void showInRightLayout(View categoryListItem, Category current) {
		Log.d("MyTAG", "showInRightLayout "+current.getName());
		int name =  R.id.rightRowName;	
		int image = R.id.rightRowImage;				
		setListener(current, getCategoryButton(categoryListItem, current, image, name));
	}

	private void showInLeftLayout(View categoryListItem, Category current) {
		Log.d("MyTAG", "showInLeftLayout "+current.getName());
		int name =  R.id.leftRowName;	
		int image = R.id.leftRowImage;
		setListener(current, getCategoryButton(categoryListItem, current, image, name));
	}

	private ImageButton getCategoryButton(View categoryListItem, Category current, int image, int name) {
		Log.d("MyTAG", "getCategoryButton "+current.getName());
		byte[] decodedString = Base64.decode(current.getImageBase64(), Base64.DEFAULT);	
		ImageButton categoryImage = (ImageButton) categoryListItem.findViewById(image);
		categoryImage.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));	
		TextView categoryName = (TextView) categoryListItem.findViewById(name);
		categoryName.setText(current.getId()+". "+current.getName());
		return categoryImage;
	}

	private void setListener(final Category current, ImageButton categoryButton) {		
		categoryButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.d("MyTAG", "setListener "+current.getName());
				current.setParent(parentCategory);
				parentCategory = current;
				((CategoryListActivity) activity).setParentCategory(current);
				((CategoryListActivity) activity).refreshMainContent();	
				((CategoryListActivity) activity).refreshHeader();
			}
		});				
	}

	private Intent createIntentToProduct() {
		final Intent intent = new Intent(activity, ProductListActivity.class);
		intent.putExtra("parentCategory",parentCategory);
		return intent;
	}
	
	public void setCategory (Category category){
		this.parentCategory = category;
	}
}
