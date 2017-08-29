package ua.com.ex.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProductCard { 
	
	private int id ;  
	
	private String name ;	

	private BigDecimal price ;

	private int discount;    

	private int quantity;
	
	private String color ;

    private String size ; 
    
    private List<ProductCardItem> children = new ArrayList<>();

    private Map<String, String> images = new HashMap<>();	

    @Override
    public String toString() {
        String productCardItem ="\n";
        for(ProductCardItem p : children){
            productCardItem += "    "+p.toString() + "\n" ;
        }        
        return "ProductCard [id=" + id + ", name=" + name + ", price=" + price + ", discount=" + discount + ", quantity=" + quantity + ", color=" + color + ", size=" + size
                + ", children :" + productCardItem + "]";
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<ProductCardItem> getChildren() {
		return children;
	}
	
	public void setChildren(List<ProductCardItem> children) {
		this.children = children;
	}

	public Map<String, String> getImages() {
		return images;
	}

	public void setImages(Map<String, String> images) {
		this.images = images;
	}    

}
