package ua.com.ex.activity;

import android.app.Activity;
import android.os.Bundle;
import ua.com.ex.R;
import ua.com.ex.activity.productCatalogItem.ProductListFooter;
import ua.com.ex.activity.productCatalogItem.ProductListHeader;
import ua.com.ex.activity.productCatalogItem.ProductListMain;
import ua.com.ex.models.Category;
import ua.com.ex.models.Page;


public class ProductListActivity extends Activity {

	private static Page page = new Page();
	private final int ITHEM_IN_SCROLL_QUANTITY = 50;
	private Category parentCategory;
	private ProductListHeader productListHeader;
	private ProductListMain productListMain;
	private ProductListFooter productListFooter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_list_activity);
		initialize();		
		showHeader();
		showMainContent();
		showFooter();			
	}

	private void initialize() {
		setParentCategory();
		createViewers();
	}

	private void setParentCategory() {
		parentCategory = getParentCategory();
	}

	private Category getParentCategory() {		
		return (Category)getIntent().getSerializableExtra("parentCategory");		
	}

	private void createViewers() {
		productListHeader = new ProductListHeader(this, parentCategory, page);
		productListMain = new ProductListMain(this, parentCategory, ITHEM_IN_SCROLL_QUANTITY, page);
		productListFooter = new ProductListFooter(this, parentCategory, ITHEM_IN_SCROLL_QUANTITY, page);
	}

	public void showHeader(){
		productListHeader.show();
	}

	public void showMainContent(){
		productListMain.show();
	}
	
	public void refreshMainContent(){
		productListMain.refresh();
	}

	public void showFooter(){
		productListFooter.show();
	}
	
	public void refreshFooter(){
		productListFooter.refresh();
	}

}
