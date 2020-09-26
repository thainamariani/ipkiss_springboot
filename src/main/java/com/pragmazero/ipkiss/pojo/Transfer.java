package com.pragmazero.ipkiss.pojo;

public class Transfer extends Event {

	private Account origin;
	private Account destination;

	public Transfer(Account origin, Account destination, double amount) {
		super(amount);
		this.setOrigin(origin);
		this.setDestination(destination);
	}

	public Account getOrigin() {
		return origin;
	}

	public void setOrigin(Account origin) {
		this.origin = origin;
	}

	public Account getDestination() {
		return destination;
	}

	public void setDestination(Account destination) {
		this.destination = destination;
	}

}