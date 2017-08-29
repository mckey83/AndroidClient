package ua.com.ex.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private int parentId;

	private String imageBase64;	

	private int productQuantity;
	
	private Category parent;
	
	private List<ProductCatalogItem> productCatalogItems = new ArrayList<>();




	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int quantityOfProducts) {
		this.productQuantity = quantityOfProducts;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
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

	public List<ProductCatalogItem> getProducts() {
		return productCatalogItems;
	}

	public void setProducts(List<ProductCatalogItem> productCatalogItems) {
		this.productCatalogItems = productCatalogItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
