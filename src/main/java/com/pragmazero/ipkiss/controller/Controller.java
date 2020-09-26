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
			return event.getDestination();	
		} else if (event.getType().equals("withdraw")) {
			return event.getOrigin();
		} else if (event.getType().equals("transfer")) {
			return event.getOrigin();
		}
		return null;
	}
	
	public Account save(Long id, Event event) {
		Account account = repository.findById(id);
		if (!account.getId().equals(null)) {
			computeBalance(account.getBalance());
			double newBalance = actualBalance + amount;
			account.setBalance(newBalance);
		} else {
			account = new Account(id, amount);
			repository.save(account);
		}
		return account;
	}
	
	public double computeBalance(Event event, double actualBalance) {
		double newBalance = actualBalance;
		if (event.getType().equals("deposit")) {
			newBalance = actualBalance + event.getAmount();
		} else if (event.getType().equals("withdraw")) {
			newBalance = actualBalance - event.getAmount();
		} //else if (event.getType().equals("transfer")) {
			//return event.getOrigin();
		//}
		return newBalance;
	}

}
