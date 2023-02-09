package adfer.springapp.SpringWebApp.repositories;

import adfer.springapp.SpringWebApp.model.Login;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoginRepository extends CrudRepository<Login,Long> {
    @EntityGraph("employees")
    Optional<Login> findById(Long id);
}
