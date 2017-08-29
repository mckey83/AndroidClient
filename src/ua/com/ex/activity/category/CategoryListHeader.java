package ua.com.ex.activity.category;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import ua.com.ex.R;
import ua.com.ex.activity.CategoryListActivity;
import ua.com.ex.models.Category;

public class CategoryListHeader {

	private Activity activity;
	private Category category;
	private LinearLayout productListHeader;

	public CategoryListHeader(Activity activity, Category category) {		
		this.activity = activity;
		this.category = category;
		this.productListHeader = (LinearLayout) activity.findViewById(R.id.header);
	} 
	
	public void refresh(){		
		productListHeader.removeAllViews();
		show();
	}

	public void show(){
		View header = getHeader();				
		addBackButton(header);		
	}	

	private View getHeader() {		
		LayoutInflater ltInflater = activity.getLayoutInflater();	
		View header = ltInflater.inflate(R.layout.category_list_header, productListHeader, true);
		return header;
	}

	private void addBackButton(View header) {
		Button button = (Button) header.findViewById(R.id.buttonBack);
		if(category.getId() == 1){
			button.setEnabled(false);
		}
		else {
			button.setEnabled(true);
		}
		button.setText(R.string.buttonBack);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {				
				((CategoryListActivity) activity).setParentCategory(category.getParent());
				((CategoryListActivity) activity).refreshMainContent();	
				((CategoryListActivity) activity).refreshHeader();
			}
		});
	}
	
	public void setCategory (Category category){
		this.category = category;
	}

}
