package com.pragmazero.ipkiss.controller;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.server.ResponseStatusException;
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
	public void reset() {
		repository = new AccountRepository();
	}
	
    @GetMapping("balance")
    public Double getBalance(@RequestParam(name = "account_id") String id){
    	Account account = repository.findById(id);
    	return Optional.ofNullable(account.getBalance()).
    			orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account does not exist"));
    }

	@PostMapping("event")
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Account> event(@RequestBody Event event) {
		Map<String, Account> response = new HashMap<String, Account>();
		if (event.getType().equals("deposit")) {
			Account destination = deposit(event.getDestination(), event);
			response.put("destination", destination);
		} else if (event.getType().equals("withdraw")) {
			Account origin = withdraw(event.getOrigin(), event);
			response.put("origin", origin);
		} else if (event.getType().equals("transfer")) {
			Account accountOrigin = withdraw(event.getOrigin(), event);
			Account accountDestination = deposit(event.getDestination(), event);
			response.put("origin", accountOrigin);
			response.put("destination", accountDestination);
		}
		return response;
	}


	public Account deposit(String destination, Event event) {
		Account account = repository.findById(destination);
		if (account.getId() != null) {
			// update account balance
			double actualBalance = account.getBalance();
			double newBalance = actualBalance + event.getAmount();
			return repository.update(account, newBalance);
		} else {
			// create new account
			account = new Account(destination, event.getAmount());
			return repository.save(account);
		}
	}

	public Account withdraw(String origin, Event event) {
		Account account = repository.findById(origin);
		if (!account.getId().equals(null)) {
			// update account balance
			double actualBalance = account.getBalance();
			double newBalance = actualBalance - event.getAmount();
			return repository.update(account, newBalance);
		}
		return new Account();
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<?> handleException(HttpServletRequest req, Exception e) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
