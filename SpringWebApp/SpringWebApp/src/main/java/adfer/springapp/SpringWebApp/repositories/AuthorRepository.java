package adfer.springapp.SpringWebApp.repositories;

import adfer.springapp.SpringWebApp.model.Author;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional
public interface AuthorRepository extends CrudRepository<Author,Long> {
    @EntityGraph(attributePaths = "books")
    Optional<Author> findById(Long id);
}
