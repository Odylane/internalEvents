package fr.formation.internalevents.service;

import org.springframework.stereotype.Service;

import fr.formation.internalevents.business.User;
import fr.formation.internalevents.dto.UserCreateDto;
import fr.formation.internalevents.repository.UserRepository;

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
    	user.setPassword(dto.getPassword());
    	repo.save(user);
    }
    	
}
