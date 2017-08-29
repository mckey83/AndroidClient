package ua.com.ex.activity.product;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import ua.com.ex.R;
import ua.com.ex.activity.MainActivity;
import ua.com.ex.models.Category;
import ua.com.ex.models.Page;
import ua.com.ex.models.ProductCatalogItem;
import ua.com.ex.services.ProductService;

public class ProductListMain {

	private static final ProductService productService = new ProductService();	
	private Activity activity;
	private Category parentCategory;	
	private LinearLayout productMainContent;	
	private int itemInScroll;
	private Page page;

	public ProductListMain(Activity activity, Category parentCategory, int itemInScroll, Page page) {		
		this.activity = activity;
		this.parentCategory = parentCategory;			
		this.itemInScroll = itemInScroll;
		this.page = page;
		this.productMainContent = (LinearLayout) activity.findViewById(R.id.productMainContent);
	}

	public void refresh(){		
		productMainContent.removeAllViews();
		show();
	}

	public void show(){		
		List<ProductCatalogItem> productCatalogItems = getProducts(page.getPage());
		if (!productCatalogItems.isEmpty()){
			showProducts(productCatalogItems);
		}
		else {
			Log.e("MyTAG", "ProductListMain  products = 0");
			activity.startActivity(new Intent(activity, MainActivity.class));
		}
	}	

	private List<ProductCatalogItem> getProducts(int page){		
		return productService.getProductsByCategoryId(parentCategory.getId(), page, itemInScroll);
	}

	private void showProducts( List<ProductCatalogItem> productCatalogItems) {
		ScrollView sv = (ScrollView)activity.findViewById(R.id.scrollProduct);
		sv.scrollTo(0, sv.getTop());

		LayoutInflater ltInflater = activity.getLayoutInflater();		
		for (ProductCatalogItem productCatalogItem : productCatalogItems) {			
			View productListItem = ltInflater.inflate(R.layout.product_list_main, productMainContent, false);
			setName(productCatalogItem, productListItem);			
			setPrice(productCatalogItem, productListItem);			
			setImage(productCatalogItem, productListItem);	
			setQuantity(productCatalogItem, productListItem);	
			productMainContent.addView(productListItem);
		}		
	}

	private void setQuantity(ProductCatalogItem productCatalogItem, View productListItem) {
		((TextView) productListItem.findViewById(R.id.tvQuantityHeader)).setText("Quantity");
		((TextView) productListItem.findViewById(R.id.tvQuantityValue)).setText(""+productCatalogItem.getQuantity());			
	}

	private void setImage(ProductCatalogItem productCatalogItem, View productListItem) {		
		String image = productCatalogItem.getImageMain();
		if (!image.isEmpty()){
			byte[] decodedString = Base64.decode(image, Base64.DEFAULT);	
			ImageView iv = (ImageView) productListItem.findViewById(R.id.imageSmall);
			iv.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));
		} else {
			Log.e("MyTAG", "ProductListMain  No Image");
		}
	}

	private void setPrice(ProductCatalogItem productCatalogItem, View productListItem) {
		((TextView) productListItem.findViewById(R.id.tvPrice)).setText("Price : " + productCatalogItem.getPrice()+" UAH");		
	}

	private void setName(ProductCatalogItem productCatalogItem, View productListItem) {
		((TextView) productListItem.findViewById(R.id.tvName)).setText(productCatalogItem.getId()+". "+productCatalogItem.getName());			
	}

}
