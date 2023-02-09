package adfer.springapp.SpringWebApp.repositories;

import adfer.springapp.SpringWebApp.model.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    @EntityGraph(attributePaths = {"store" ,"login"})
    Optional<Employee> findById(Long id);
}
