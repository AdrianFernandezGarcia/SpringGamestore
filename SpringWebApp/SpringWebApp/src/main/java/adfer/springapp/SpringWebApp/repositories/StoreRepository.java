package adfer.springapp.SpringWebApp.repositories;

import adfer.springapp.SpringWebApp.model.Store;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StoreRepository extends CrudRepository<Store,Long> {
    @EntityGraph(attributePaths = {"games" ,"employees"})
    Optional<Store> findById(Long id);

}
