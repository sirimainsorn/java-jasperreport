package com.example.javajasperreport.model;

public class Invoice {
    private int id;
	private String description;
	private String qty;
	private String unit;
	private String total;

    public Invoice(int id, String description, String qty, String unit, String total) {
		this.id = id;
		this.description = description;
		this.qty = qty;
		this.unit = unit;
		this.total = total;
	}

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

    public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

    public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
}
