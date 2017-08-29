package ua.com.ex.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductCatalogItem implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;

	private String name;
	
	private BigDecimal price;

	private int discount;    

	private int quantity;

	private String color;

	private String size;    

	private String imageMain;   

	@Override
	public int hashCode() {
		return 31 + id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductCatalogItem other = (ProductCatalogItem) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductCatalogItem [id=" + id + ", name=" + name + ", price=" + price + ", discount=" + discount + ", quantity=" + quantity + ", color=" + color + ", size=" + size
				+ ", imageMain=" + imageMain + "]";
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

	public String getImageMain() {
		return imageMain;
	}

	public void setImageMain(String imageMain) {
		this.imageMain = imageMain;

	}

}
