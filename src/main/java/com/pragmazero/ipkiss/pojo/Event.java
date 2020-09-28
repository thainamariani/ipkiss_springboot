package com.pragmazero.ipkiss.pojo;

public class Event {

	private String type;
	private String origin;
	private String destination;
	private Double amount;

	public Event(String type, Double amount) {
		super();
		this.type = type;
		this.amount = amount;
	}
	
	public Event() {
		super();
	}

	public Event(String type, String origin, String destination, Double amount) {
		super();
		this.type = type;
		this.origin = origin;
		this.destination = destination;
		this.amount = amount;
	}

	public Event(String type, String destination, Double amount) {
		super();
		this.type = type;
		this.destination = destination;
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}



	public String getOrigin() {
		return origin;
	}



	public void setOrigin(String origin) {
		this.origin = origin;
	}



	public String getDestination() {
		return destination;
	}



	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	
}
