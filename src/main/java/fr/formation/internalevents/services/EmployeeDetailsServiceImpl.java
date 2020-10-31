package fr.formation.internalevents.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.internalevents.config.EmployeeDetails;
import fr.formation.internalevents.config.ResourceNotFoundException;
import fr.formation.internalevents.dtos.EmployeeAuthDto;
import fr.formation.internalevents.dtos.EmployeeInfoDto;
import fr.formation.internalevents.repositories.EmployeeRepository;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {
	
	private final EmployeeRepository employeeRepository;
	
	protected EmployeeDetailsServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	 // Throws UsernameNotFoundException (Spring contract)
    @Override
    public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException {
	EmployeeAuthDto user = employeeRepository.findByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException(
			"no employee found with username: " + username));
	return new EmployeeDetails(user);
    }

    // Throws ResourceNotFoundException (restful practice)
    @Override
    public EmployeeInfoDto getCurrentEmployeeInfo(Long id) {
    	return employeeRepository.getById(id).orElseThrow(() -> new ResourceNotFoundException("with id:" + id));
    }
}
