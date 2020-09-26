package com.pragmazero.ipkiss.pojo;

public class Deposit extends Event{
	
	private Account destination;

	public Deposit(Account destination, double amount) {
		super(amount);
		this.destination = destination;
	}

	public Account getDestination() {
		return destination;
	}

	public void setDestination(Account destination) {
		this.destination = destination;
	}

}
