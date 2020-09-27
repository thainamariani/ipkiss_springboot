package com.pragmazero.ipkiss.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pragmazero.ipkiss.pojo.Account;
import com.pragmazero.ipkiss.pojo.Event;
import com.pragmazero.ipkiss.repository.AccountRepository;
import com.pragmazero.ipkiss.repository.Deposit;
import com.pragmazero.ipkiss.repository.Transfer;
import com.pragmazero.ipkiss.repository.Withdraw;

//import com.pragmazero.ipkiss.repository.AccountRepository;

@ControllerAdvice
@RestController
public class Controller extends ResponseEntityExceptionHandler {

	// @Autowired
	public AccountRepository repository;

	@GetMapping("/")
	public String home() {
		return "Hi";
	}

	@PostMapping("reset")
	public void reset() {
		repository = new AccountRepository();
	}

	@PostMapping("event")
	public Long event(Event event) {
		if (event.getType().equals("deposit")) {
			deposit(event.getDestination(), event);
		} else if (event.getType().equals("withdraw")) {
			withdraw(event.getOrigin(), event);
		} else if (event.getType().equals("transfer")) {
			transfer(event.getOrigin(), event.getDestination());
		}
		return null;
	}

	private void transfer(Long origin, Long destination, Event event) {
		withdraw(origin, event);
		
		
	}

	public void deposit(Long destination, Event event) {
		Account account = repository.findById(destination);
		if (!account.getId().equals(null)) {
			//update account balance
			double actualBalance = account.getBalance();
			double newBalance = actualBalance + event.getAmount();
			repository.update(account, newBalance);
		} else {
			//create new account
			account = new Account(destination, event.getAmount());
			repository.save(account);
		}
		//return account;
	}
	
	private void withdraw(Long origin, Event event) {
		Account account = repository.findById(origin);
		if (!account.getId().equals(null)) {
			//update account balance
			double actualBalance = account.getBalance();
			double newBalance = actualBalance - event.getAmount();
			repository.update(account, newBalance);
		} else {
			//account not found
		}
	}

}
