package fr.formation.internalevents.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.internalevents.dto.UserCreateDto;
import fr.formation.internalevents.service.UserService;


@RestController
@RequestMapping(path="/events") // This means URL starts with /events (after Application path)
@CrossOrigin("*")
public class UserController {
	
	private final UserService service;

    protected UserController(UserService user) {
	this.service = user;
    }
	
    /**
     * Creates a new user with given username and password, and default role.
     *
     * @param inputs the inputs
     */
	@PostMapping(path="/add")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void addNewAccount(@Valid @RequestBody UserCreateDto inputs) {
		service.create(inputs);
	}
	
	  
}
