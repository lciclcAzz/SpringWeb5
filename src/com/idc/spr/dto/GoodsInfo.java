package com.idc.spr.dto;

import java.io.Serializable;

public class GoodsInfo implements Serializable{
	private static final long serialVersionUID =1L;
	private String productId;
	private String productName;
	private String typeProductId;
	private String brandProductId;
	private double priceCost;
	private double priceSale;
	private int productItem;
	private String productDateStart;
	private String productDateExpire;
	private String productDesc;
	private String deleteFlag;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getTypeProductId() {
		return typeProductId;
	}
	public void setTypeProductId(String typeProductId) {
		this.typeProductId = typeProductId;
	}
	public String getBrandProductId() {
		return brandProductId;
	}
	public void setBrandProductId(String brandProductId) {
		this.brandProductId = brandProductId;
	}
	public double getPriceCost() {
		return priceCost;
	}
	public void setPriceCost(double priceCost) {
		this.priceCost = priceCost;
	}
	public double getPriceSale() {
		return priceSale;
	}
	public void setPriceSale(double priceSale) {
		this.priceSale = priceSale;
	}
	public int getProductItem() {
		return productItem;
	}
	public void setProductItem(int productItem) {
		this.productItem = productItem;
	}
	public String getProductDateStart() {
		return productDateStart;
	}
	public void setProductDateStart(String productDateStart) {
		this.productDateStart = productDateStart;
	}
	public String getProductDateExpire() {
		return productDateExpire;
	}
	public void setProductDateExpire(String productDateExpire) {
		this.productDateExpire = productDateExpire;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
