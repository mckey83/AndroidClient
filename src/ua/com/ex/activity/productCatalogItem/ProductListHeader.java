package ua.com.ex.activity.productCatalogItem;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import ua.com.ex.R;
import ua.com.ex.activity.CategoryListActivity;
import ua.com.ex.models.Category;
import ua.com.ex.models.Page;

public class ProductListHeader {

	private Activity activity;
	private Category parentCategory;
	private Page page;

	public ProductListHeader(Activity activity, Category parentCategory, Page page){
		this.activity = activity;
		this.parentCategory = parentCategory;
		this.page = page;
		
	}

	public void show(){
		View header = getHeader();				
		addBackButton(header);
		addChartButton(header);
	}
	
	private View getHeader() {
		LinearLayout productListHeader = (LinearLayout) activity.findViewById(R.id.header);
		LayoutInflater ltInflater = activity.getLayoutInflater();			
		return ltInflater.inflate(R.layout.product_list_header, productListHeader, true);
	}

	private void addBackButton(View header) {		
		Button button = (Button) header.findViewById(R.id.buttonBack);
		button.setText(R.string.buttonBack);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {	
				page.setNull();
				activity.startActivity(createIntent());	
			}
		});
	}

	private Intent createIntent() {
		final Intent intentBack = new Intent(activity, CategoryListActivity.class);
		intentBack.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return intentBack.putExtra("parentCategory",parentCategory.getParent());
	}
	
	private void addChartButton(View header) {		
		Button button = (Button) header.findViewById(R.id.buttonChart);
		button.setText(R.string.buttonChart);
		
	}	

}
