package com.pragmazero.ipkiss.pojo;

public abstract class Event {

	private double amount;	

	public Event(double amount) {
		super();
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
