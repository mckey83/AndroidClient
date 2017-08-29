package ua.com.ex.activity.productCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import ua.com.ex.R;
import ua.com.ex.activity.MainActivity;
import ua.com.ex.models.ProductCard;
import ua.com.ex.models.ProductCardItem;
import ua.com.ex.services.ProductCardService;

public class ProductCardMain {

	private class SizeColorQuantity implements Comparable<SizeColorQuantity> {
		String size;
		List<ColorQuantity> available = new ArrayList<>();
		@Override
		public int compareTo(SizeColorQuantity another) {
			// TODO Auto-generated method stub
			return this.size.compareTo(another.size);
		}

	}

	private class ColorQuantity {
		String colour;
		String quantity;
	}

	public ProductCardMain (Activity activity, int productCardId) {		
		this.activity = activity;
		this.productCardId = productCardId;
		this.productCardMainContent = (LinearLayout) this.activity.findViewById(R.id.productCardMainContent);
	}

	private Activity activity;
	private int productCardId;
	private LinearLayout productCardMainContent;
	private ProductCard productCard = new ProductCard();
	private static final ProductCardService productCardService = new ProductCardService();

	public void show(){
		productCard = productCardService.getProductCardById(productCardId);
		if(productCard.getId() == 0){
			Log.d("MyTAG", "ProductCardMain Intent ");
			activity.startActivity(new Intent(activity, MainActivity.class));			
		} else {
			//Log.d("MyTAG", "ProductCardMain show() "+ productCard);

			String image = productCard.getImages().get(productCard.getColor());
			if(!image.isEmpty()){
				byte[] decodedString = Base64.decode(image, Base64.DEFAULT);	
				ImageView imMainImage = (ImageView)activity.findViewById(R.id.productCardMainContentMainImage);
				imMainImage.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));	
				imMainImage.setLayoutParams(new LinearLayout.LayoutParams(200, 100));
			}

			TextView tvId = (TextView)activity.findViewById(R.id.productCardMainContentId);
			tvId.setText("id. "+productCard.getId());

			TextView tvName = (TextView)activity.findViewById(R.id.productCardMainContentName);
			tvName.setText("name : "+productCard.getName());

			TextView tvPrice = (TextView)activity.findViewById(R.id.productCardMainContentPrice);
			tvPrice.setText("price : "+productCard.getPrice().toString()+" UAH");

			int discount = productCard.getDiscount();
			if(discount > 0){
				TextView tvDiscount= (TextView)activity.findViewById(R.id.productCardMainContentDiscount);
				tvDiscount.setText("discount : "+discount);
			}
			Log.d("MyTAG", "ProductCardMain show() children ");

			Set<String> usedColor = new HashSet<>();
			if(productCard.getChildren().size() >0 ){	
				for (ProductCardItem item : productCard.getChildren()) {					
					String color = item.getColor();
					if (!usedColor.contains(color)){
						usedColor.add(color);
						LinearLayout productItemImages  = (LinearLayout)activity.findViewById(R.id.productCardItemColorImage);
						LayoutInflater ltInflater = activity.getLayoutInflater();
						View productImage = ltInflater.inflate(R.layout.product_card_item_image, productItemImages, false);
						if(setImage(item, productImage)){						
							productItemImages.addView(productImage);
						}
					}
				}
			}
			Log.d("MyTAG", "ProductCardMain show() children - OK");
			Log.d("MyTAG", "ProductCardMain show()createTable()");
			createTable();
			Log.d("MyTAG", "ProductCardMain show()createTable() - OK");
			Log.d("MyTAG", "ProductCardMain show() -OK "+ productCard);
		}
	}


	private boolean setImage(ProductCardItem item, View productImage) {
		Log.d("MyTAG", "ProductCardMain setImage() children ");
		String image = productCard.getImages().get(item.getColor());
		if(image!=null){
			Log.d("MyTAG", "image.size "+image.length());
			if(!image.isEmpty()){
				byte[] decodedString = Base64.decode(image, Base64.DEFAULT);	
				ImageButton ib = (ImageButton) productImage.findViewById(R.id.itemColor);
				ib.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));
				ib.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
				return true;
			}
		}else{
			Log.d("MyTAG", "ProductCardMain show() setImage image == null "+item.getId()+ "color = "+item.getColor());			
		}
		return false;
	}




	private void createTable() {	

		Set <String> colourAvailable = new HashSet<>();		
		for (ProductCardItem item : productCard.getChildren()){
			String colour = item.getColor();
			if (!colourAvailable.contains(colour)){
				colourAvailable.add(colour);
			}
		}


		List<String> colourAvailableList = new ArrayList<>(colourAvailable);

		java.util.Collections.sort(colourAvailableList);

		LinearLayout table  = (LinearLayout)activity.findViewById(R.id.tableContent);
		LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		LayoutInflater ltInflater = activity.getLayoutInflater();
		LinearLayout tableColumn = (LinearLayout)ltInflater.inflate(R.layout.product_card_size_color_quantity_table, table, false);

		if(colourAvailableList.size()>0){
			TextView colourColumn = new TextView(activity);			
			colourColumn.setText("colours");
			colourColumn.setLayoutParams(params);
			tableColumn.addView(colourColumn);	


			for (String item : colourAvailableList) {			
				colourColumn = new TextView(activity);			
				colourColumn.setText(item);
				colourColumn.setLayoutParams(params);
				tableColumn.addView(colourColumn);			
			}
			table.addView(tableColumn);




			Map <String, Map<String, String>> size_color_quantity = new HashMap<>();


			for (ProductCardItem item : productCard.getChildren()){			
				String size = item.getSize();
				if (!size_color_quantity.containsKey(size)){
					Map<String, String> color_quantity = new HashMap<>();
					color_quantity.put(item.getColor(), ""+item.getQuantity());
					size_color_quantity.put(size, color_quantity);
				}
				else{
					Map<String, String> color_quantity = size_color_quantity.get(size);
					String colour = item.getColor();
					String quantity = ""+item.getQuantity();
					if(!color_quantity.containsKey(colour)){
						color_quantity.put(colour, quantity);
					}
				}
			}


			List<SizeColorQuantity> sizeColorQuantityAll = new ArrayList<>();
			for(Map.Entry<String,  Map<String, String>> entry :  size_color_quantity.entrySet()){			
				SizeColorQuantity sizeColorQuantityItem = new SizeColorQuantity();
				sizeColorQuantityItem.size = entry.getKey();
				for(Map.Entry<String, String> buff : entry.getValue().entrySet()) {
					ColorQuantity colorQuantity = new ColorQuantity();
					colorQuantity.colour = buff.getKey();
					colorQuantity.quantity = buff.getValue();
					sizeColorQuantityItem.available.add(colorQuantity);
				}
				sizeColorQuantityAll.add(sizeColorQuantityItem);
			}

			java.util.Collections.sort(sizeColorQuantityAll);
			for(SizeColorQuantity item : sizeColorQuantityAll){
				tableColumn = (LinearLayout)ltInflater.inflate(R.layout.product_card_size_color_quantity_table, table, false);
				TextView colour = new TextView(activity);			
				colour.setText(item.size);
				colour.setLayoutParams(params);
				tableColumn.addView(colour);			
				String colourOut = "";
				for(String colourItem :colourAvailableList){

					for(ColorQuantity colorQuantity : item.available){
						if(colorQuantity.colour.equals(colourItem)){
							if(colorQuantity.quantity.equals("0")) {
								colourOut = " - ";
							}else{
								colourOut = "  "+colorQuantity.quantity+"  ";
							}
							break;
						}
						else{
							colourOut = " - ";
						}
					}				
					colour = new TextView(activity);			
					colour.setText(colourOut);
					colour.setLayoutParams(params);
					tableColumn.addView(colour);				
				}
				table.addView(tableColumn);
			}

		}
	}



}





