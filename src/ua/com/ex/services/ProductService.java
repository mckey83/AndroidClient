package ua.com.ex.services;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ua.com.ex.models.ProductCatalogItem;
import ua.com.ex.models.Settings;


public 	class ProductService  {

	private String path  = Settings.path+"product/";

	public List<ProductCatalogItem> getProductsByCategoryId(int id, int page, int itemQuantity){
		String gson = "";		
		gson = new Transport().getContent(new String(path+id+"/category/"+page+"/page/"+itemQuantity));				
		Type listType = new TypeToken<ArrayList<ProductCatalogItem>>(){}.getType();
		List<ProductCatalogItem> yourClassList = new Gson().fromJson(gson, listType);		
		return yourClassList;
	}	
	
}



