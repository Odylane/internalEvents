package fr.formation.internalevents.services;

import org.springframework.stereotype.Service;

import fr.formation.internalevents.dtos.UserCreateDto;
import fr.formation.internalevents.entities.User;
import fr.formation.internalevents.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
    private final UserRepository repo;

    protected UserServiceImpl(UserRepository repo) {
	this.repo = repo;
    }

    @Override
    public void create(UserCreateDto dto) {
    	
    	User user = new User();
    	user.setUsername(dto.getUsername());
    	user.setFirstname(dto.getFirstname());
    	user.setLastname(dto.getLastname());
    	user.setEmail(dto.getEmail());
    	user.setPassword(dto.getPassword());
    	repo.save(user);
    }	
}
