package net.javaguides.ems_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.javaguides.ems_backend.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find employee by email for login
    Optional<Employee> findByEmail(String email);
}
