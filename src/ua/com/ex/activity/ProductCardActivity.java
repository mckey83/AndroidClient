package ua.com.ex.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import ua.com.ex.R;
import ua.com.ex.activity.productCard.ProductCardMain;


public class ProductCardActivity extends Activity {

	private ProductCardMain productCardMain;
	private int productCardId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_card);		
		getProductCardId();
		createViewers();
		showMainContent(); 
	}

	private void getProductCardId() {
		String productCardId = getIntent().getStringExtra("productCardId");
		if(!productCardId.isEmpty()){
			this.productCardId = Integer.parseInt(productCardId);
		}
		else{

		}
		Log.d("MyTAG", "productCardId = " + productCardId);
	}
	
	private void createViewers() {
		productCardMain = new ProductCardMain(this, productCardId);
	}
	
	private void showMainContent() {		
		productCardMain.show();
	}
}
