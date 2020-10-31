package fr.formation.internalevents.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.internalevents.dtos.EmployeeCreateDto;
import fr.formation.internalevents.services.EmployeeService;


@RestController
@RequestMapping(path="/employees")
public class EmployeeController {
	
	private final EmployeeService service;

    protected EmployeeController(EmployeeService employee) {
	this.service = employee;
    }
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void addNewAccount(@Valid @RequestBody EmployeeCreateDto inputs) {
		service.create(inputs);
	}
	
	  
}
