package ua.com.ex.models;

import java.math.BigDecimal;

public class ProductCardItem {    

	private int id ;

	private BigDecimal price ;

	private int discount;  

	private int quantity;

	private String color ;

	private String size ;	

	@Override
	public String toString() {
		return "ProductCardItem [id=" + id + ", price=" + price + ", discount=" + discount + ", quantity=" + quantity + ", color=" + color + ", size=" + size + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
