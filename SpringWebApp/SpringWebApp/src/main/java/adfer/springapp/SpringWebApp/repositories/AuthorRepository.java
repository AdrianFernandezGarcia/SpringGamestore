package adfer.springapp.SpringWebApp.repositories;
import adfer.springapp.SpringWebApp.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Long> {
}
