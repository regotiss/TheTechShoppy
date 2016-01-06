package model;

import java.io.Serializable;

//Represent a single book i.e. it contain all necessary details of a book

public class ProductBean implements Serializable{

	
	public ProductBean(double d, String imgPath, String title, String storeLink) {
		
		this.price = d;
		this.imgPath = imgPath;
		this.title = title;
		this.storeLink = storeLink;
	}
	private double price;
	private String imgPath;
	private String title;
	private String storeLink;
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStoreLink() {
		return storeLink;
	}
	public void setStoreLink(String storeLink) {
		this.storeLink = storeLink;
	}

}
