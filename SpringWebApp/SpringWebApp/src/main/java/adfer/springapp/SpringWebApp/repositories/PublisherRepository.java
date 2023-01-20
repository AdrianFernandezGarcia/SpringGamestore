package adfer.springapp.SpringWebApp.repositories;

import adfer.springapp.SpringWebApp.model.Author;
import adfer.springapp.SpringWebApp.model.Publisher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PublisherRepository extends CrudRepository<Publisher,Long> {
    @EntityGraph(attributePaths = "books")
    Optional<Publisher> findById(Long id);
}
