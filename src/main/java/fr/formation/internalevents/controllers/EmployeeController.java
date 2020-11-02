package fr.formation.internalevents.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.internalevents.dtos.EmployeeCreateDto;
import fr.formation.internalevents.services.EmployeeService;


@RestController
@RequestMapping
public class EmployeeController {
	
	private final EmployeeService service;

    protected EmployeeController(EmployeeService service) {
	this.service = service;
    }
	
	@PostMapping("/register")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void addNewAccount(@Valid @RequestBody EmployeeCreateDto inputs) {
		service.create(inputs);
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user")
	public String user() {
		return "Hello user!";
	}
	
    @GetMapping("/authenticated")
    public String secured() {
	return "Hello authenticated user!";
    }
    
}
