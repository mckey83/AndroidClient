package ua.com.ex.models;

public class Page {
	int page;
	
	public int getPage(){
		return page;
	}

	public void increase(){
		page++;
	}
	
	public void decrease(){
		page--;
	}
	
	public void setNull(){
		page = 0;
	}
}
