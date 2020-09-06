package fr.formation.internalevents.service;

import fr.formation.internalevents.dto.UserCreateDto;

public interface UserService {
	
    /**
     * Creates and saves a new {@code User} with given credentials and
     * default role.
     *
     * @param dto a new user credentials
     * @throws Exception if a {@code User} already exists with given
     * username
     */
	void create(UserCreateDto dto);
		
}
