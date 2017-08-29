package ua.com.ex.activity.productCatalogItem;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import ua.com.ex.R;
import ua.com.ex.activity.ProductListActivity;
import ua.com.ex.models.Category;
import ua.com.ex.models.Page;

public class ProductListFooter {

	private Activity activity;
	private Category parentCategory;	
	private LinearLayout productMainFooter;	
	private int itemQuantity;
	private Page page;
	private LinearLayout footer;

	public ProductListFooter(Activity activity, Category parentCategory, int itemQuantity, Page page) {		
		this.activity = activity;
		this.parentCategory = parentCategory;		
		this.itemQuantity = itemQuantity;
		this.page = page;
		
	}

	public void show() {
		createFooter();		
		setButtonNextPage();
		setButtonPreviousPage();
	}

	private void createFooter() {	
		
		this.productMainFooter = (LinearLayout) activity.findViewById(R.id.productMainFooter);
		productMainFooter.removeAllViews();
		this.footer = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.product_list_footer, productMainFooter, true);	
	}

	private void setButtonNextPage() {
		Button buttonNextPage = (Button)footer.findViewById(R.id.buttonNextPage);		
		setButtonNextState(buttonNextPage);		
		buttonNextPage.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				page.increase();
				refreshMainContent();
				show();
			}
		});
	}

	private void setButtonNextState(final Button buttonNextPage) {
		int remainProduct = getRemainProduct();
		if (remainProduct > 0){			
			buttonNextPage.setEnabled(true);			
		} else {
			buttonNextPage.setEnabled(false);			
		}
	}

	private int getRemainProduct() {		
		int quantityOfProductInCategory = parentCategory.getProductQuantity();
		int remainProduct = quantityOfProductInCategory - ((page.getPage()+1)*itemQuantity);
		return remainProduct;
	}

	private void setButtonPreviousPage() {
		Button buttonPreviousPage = (Button)footer.findViewById(R.id.buttonPreviousPage);
		setButtonPreviousState(buttonPreviousPage);
		buttonPreviousPage.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				page.decrease();			
				refreshMainContent();
				show();
			}
		});
	}

	private void setButtonPreviousState(final Button buttonPreviousPage) {
		if (page.getPage() < 1){
			page.setNull();
			buttonPreviousPage.setEnabled(false);			
		} else {
			buttonPreviousPage.setEnabled(true);			
		}
	}

	private void refreshMainContent() {	
		((ProductListActivity) activity).refreshMainContent();
		((ProductListActivity) activity).refreshFooter();	
	}	

	public void refresh(){		
		show();
	}

}
