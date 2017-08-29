package ua.com.ex.activity;

import android.app.Activity;
import android.os.Bundle;
import ua.com.ex.R;
import ua.com.ex.activity.category.CategoryListHeader;
import ua.com.ex.activity.category.CategoryListMain;
import ua.com.ex.models.Category;

public class CategoryListActivity extends Activity {

	private Category parentCategory;
	private CategoryListHeader categoryListHeader;
	private CategoryListMain categoryListMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_list_activity);				 

		initializeParentCategory();	
		createViewers();	 
		showHeader();
		showMainContent();
	}		

	private void initializeParentCategory() {		
		parentCategory =  (Category)getIntent().getSerializableExtra("parentCategory");		
	}	
	private void createViewers() {
		categoryListHeader = new CategoryListHeader(this, parentCategory);
		categoryListMain = new CategoryListMain(this, parentCategory);		
	}

	private void showHeader() {
		categoryListHeader.show();
		categoryListMain = new CategoryListMain(this, parentCategory);		
	}

	private void showMainContent() {		
		categoryListMain.show();
	}
	
	public void refreshMainContent() {
		categoryListMain.setCategory(parentCategory);
		categoryListMain.refresh();
	}
	
	public void refreshHeader() {	
		categoryListHeader.setCategory(parentCategory);
		categoryListHeader.refresh(); 
	}


	public void setParentCategory(Category parentCategory){
		this.parentCategory = parentCategory;
	}


}
