package com.pragmazero.ipkiss.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pragmazero.ipkiss.pojo.Account;
import com.pragmazero.ipkiss.pojo.Event;
import com.pragmazero.ipkiss.repository.AccountRepository;

@ControllerAdvice
@RestController
public class Controller extends ResponseEntityExceptionHandler {

	public AccountRepository repository;

	@GetMapping("/")
	public String home() {
		return "Hi";
	}

	@PostMapping("reset")
	public String reset() {
		repository = new AccountRepository();
		return "OK";
	}
	
    @GetMapping("balance")
    public Double getBalance(@RequestParam(name = "account_id") String id){
    	System.out.println(id);
    	Account account = repository.findById(id);
    	return Optional.ofNullable(account.getBalance()).
    			orElseThrow(() -> new NullPointerException());
    }

	@PostMapping("event")
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Account> event(@RequestBody Event event) {
		Map<String, Account> response = new HashMap<String, Account>();
		
		if (event.getType().equals("deposit")) {
			Account destination = deposit(event);
			response.put("destination", destination);
			
		} else if (event.getType().equals("withdraw")) {
			Account origin = withdraw(event);
			response.put("origin", origin);
			
		} else if (event.getType().equals("transfer")) {
			Account accountOrigin = withdraw(event);
			Account accountDestination = deposit(event);
			response.put("origin", accountOrigin);
			response.put("destination", accountDestination);
		}
		return response;
	}

	public Account deposit(Event event) {
		String destination = event.getDestination();
		Account account = repository.findById(destination);
		
		// update account balance if destination exist
		if (account.getId() != null) {
			double actualBalance = account.getBalance();
			double newBalance = actualBalance + event.getAmount();
			return repository.update(account, newBalance);
			
		// create new account if destination does not exist
		} else {
			account = new Account(destination, event.getAmount());
			return repository.save(account);
		}
	}

	public Account withdraw(Event event) {
		String origin = event.getOrigin();
		Account account = repository.findById(origin);
		
		//update account balance if origin exists
		if (!account.getId().equals(null)) {
			double actualBalance = account.getBalance();
			double newBalance = actualBalance - event.getAmount();
			return repository.update(account, newBalance);
		}
		//return empty object Account if origin does not exist
		return new Account();
	}

	//handle all exceptions by returning 404 0
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<?> handleException(HttpServletRequest req, Exception e) {
		return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
	}

}
