package com.example.javajasperreport.model;

public class Invoice {
    private int id;
	private String description;
	private String qty;
	private String rate;
	private String amount;

    public Invoice(int id, String description, String qty, String rate, String amount) {
		this.id = id;
		this.description = description;
		this.qty = qty;
		this.rate = rate;
		this.amount = amount;
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

    public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

    public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}
