package model;

import javax.validation.constraints.Min;

public class Item {
	private String stockID;
	private String itemName;
	private Double unitPrice;
	private Integer onStock;
	
	public Item() {}

	public Item(String stockID, String itemName, Double unitPrice, Integer onStock) {
		this.stockID = stockID;
		this.itemName = itemName;
		this.unitPrice = unitPrice;
		this.onStock = onStock;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getOnStock() {
		return onStock;
	}

	public String getStockID() {
		return stockID;
	}
	
	public void setStockID(String stockID) {
		this.stockID = stockID;
	}
	
	public void setOnStock(Integer onStock) {
		this.onStock = onStock;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
}
