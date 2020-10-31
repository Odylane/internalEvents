package fr.formation.internalevents.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.internalevents.dtos.EmployeeAuthDto;
import fr.formation.internalevents.dtos.EmployeeInfoDto;
import fr.formation.internalevents.entities.Employee;

// create the repository that holds user records
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	/**
     * Retrieves a projected view of the {@code Employee} with given username.
     *
     * @param username a username
     * @return a projected view
     */
    Optional<EmployeeAuthDto> findByUsername(String username);

    /**
     * Retrieves a projected view of the current authenticated
     * {@code Employee}.
     *
     * @param id employee id
     * @return a projected view
     */
    Optional<EmployeeInfoDto> getById(Long id);	
	
}
