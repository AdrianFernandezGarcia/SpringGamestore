package adfer.springapp.SpringWebApp.repositories;
import adfer.springapp.SpringWebApp.model.Book;
import adfer.springapp.SpringWebApp.model.Publisher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book,Long> {
    @EntityGraph(attributePaths = {"authors" ,"publisher" })
    Optional<Book> findById(Long id);
}
