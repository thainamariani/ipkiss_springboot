package com.pragmazero.ipkiss.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pragmazero.ipkiss.pojo.Event;
import com.pragmazero.ipkiss.repository.AccountRepository;

//import com.pragmazero.ipkiss.repository.AccountRepository;

@ControllerAdvice
@RestController
public class Controller extends ResponseEntityExceptionHandler {

	//@Autowired
	public AccountRepository repository;

	@GetMapping("/")
	public String home() {
		return "Hi";
	}
	
    @PostMapping("reset")
    public void reset(){
        repository = new AccountRepository();
    }
}
