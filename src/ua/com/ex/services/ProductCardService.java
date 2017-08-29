package ua.com.ex.services;

import com.google.gson.Gson;

import android.util.Log;
import ua.com.ex.models.ProductCard;
import ua.com.ex.models.Settings;

public class ProductCardService {
	
	private String path = Settings.path+"product/";

	public ProductCard getProductCardById(int id){		
		ProductCard result;
		String gson = "";		
		Log.d("MyTAG", "getProductCardById id "+ id);
		gson = new Transport().getContent(path + id);	
		Log.d("MyTAG", "getProductCardById = "+ gson);
		if(!gson.isEmpty()){
			result = new Gson().fromJson(gson, ProductCard.class);
			if (result.getId() == 0){
				Log.d("MyTAG", "getProductCardById return new ProductCard() ");
				result = new ProductCard();
				result.setId(0);
			}
		}
		else {		
			Log.d("MyTAG", "getProductCardById return new ProductCard() ");
			result = new ProductCard();
			result.setId(0);
		}		
		return result;
	}
	
	

}
