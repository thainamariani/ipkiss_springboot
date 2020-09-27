package com.pragmazero.ipkiss.repository;

import java.util.ArrayList;
import java.util.List;

import com.pragmazero.ipkiss.pojo.Account;

public class AccountRepository {

	private List<Account> accounts;

	public AccountRepository() {
		accounts = new ArrayList<>();
	}

	public Account save(Account account) {
		accounts.add(account);
		return account;
	}

	public Account update(Account account, double newBalance) {
		account.setBalance(newBalance);
		return account;
	}

	public Account findById(Long id) {
		for (Account account : accounts) {
			if (account.getId() == id) {
				return account;
			}
		}
		return new Account();
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
