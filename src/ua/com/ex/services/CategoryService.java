package ua.com.ex.services;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.util.Log;
import ua.com.ex.models.Category;
import ua.com.ex.models.Settings;

public class CategoryService {

	private String path;

	public List<Category> getCategoryByParentId(String parenId){
		setPath();
		List<Category> result;
		String gson = "";		
		gson = new Transport().getContent(new String(path +parenId+"/parentid"));	
		if(!gson.isEmpty()){
			Type listType = new TypeToken<ArrayList<Category>>(){}.getType();
			result = new Gson().fromJson(gson, listType);
		}
		else {
			result = new ArrayList<>();			
		}
		return result;
	}
	
	public Category getCategoryById(String parenId){
		setPath();
		String gson = "";	
		Category result;
		gson = new Transport().getContent(new String(path+parenId));
		if(!gson.isEmpty()){
			result = new Gson().fromJson(gson, Category.class);
		}
		else {
			result = new Category();
		}
		return result;
	}
	
	private void setPath() {
		Log.d("MyTAG", "CategoryService  path = "+ Settings.path);
		path = Settings.path+"category/";
	}
}
