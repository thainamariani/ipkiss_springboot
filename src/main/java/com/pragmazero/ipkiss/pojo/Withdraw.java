package com.pragmazero.ipkiss.pojo;

public class Withdraw extends Event {
	
	private Account origin;

	public Withdraw(Account origin, double amount) {
		super(amount);
		this.setOrigin(origin);
	}

	public Account getOrigin() {
		return origin;
	}

	public void setOrigin(Account origin) {
		this.origin = origin;
	}

}
