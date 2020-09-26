package com.pragmazero.ipkiss.pojo;

public class Event {

	private String type;
	private Long origin;
	private Long destination;
	private double amount;

	public Event(String type, double amount) {
		super();
		this.type = type;
		this.amount = amount;
	}
	
	
	
	
	public Event() {
		super();
	}




	public Event(String type, Long origin, Long destination, double amount) {
		super();
		this.type = type;
		this.origin = origin;
		this.destination = destination;
		this.amount = amount;
	}



	public Event(String type, Long destination, double amount) {
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
	

	/*public Long getDestination() {
		return destination;
	}


	public void setDestination(Long destination) {
		this.destination = destination;
	}*/


	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}



	public Long getOrigin() {
		return origin;
	}



	public void setOrigin(Long origin) {
		this.origin = origin;
	}



	public Long getDestination() {
		return destination;
	}



	public void setDestination(Long destination) {
		this.destination = destination;
	}
	
	
	
}
