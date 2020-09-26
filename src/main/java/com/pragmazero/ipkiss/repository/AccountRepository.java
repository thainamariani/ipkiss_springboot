package com.pragmazero.ipkiss.repository;

import java.util.ArrayList;
import java.util.List;

import com.pragmazero.ipkiss.pojo.Account;
import com.pragmazero.ipkiss.pojo.Event;

public class AccountRepository {

	private List<Account> accounts;

	public AccountRepository() {
		accounts = new ArrayList<>();
	}

	public Account save(Account account) {
		accounts.add(account);
		return account;
	}

	public Account update(Account account, double balance) {
		double balance = account.getBalance();

		if (event instanceof Deposit) {
			account.setBalance(balance + event.getAmount());
		} else if (event instanceof Withdraw) {
			account.setBalance(balance - event.getAmount());
		} else if (event instanceof Transfer) {

		}
		return account;
	}

	public Account findById(Long id) {
		for (Account account : accounts) {
			if (account.getId().equals(id)) {
				return account;
			}
		}
		return new Account();
	}
}
