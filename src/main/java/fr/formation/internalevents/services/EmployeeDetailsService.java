package fr.formation.internalevents.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.formation.internalevents.dtos.EmployeeInfoDto;

public interface EmployeeDetailsService extends UserDetailsService {
	
	EmployeeInfoDto getCurrentEmployeeInfo(Long id);

}
