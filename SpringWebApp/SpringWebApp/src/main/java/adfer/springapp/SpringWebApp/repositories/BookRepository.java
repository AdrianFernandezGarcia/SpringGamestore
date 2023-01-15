package adfer.springapp.SpringWebApp.repositories;
import adfer.springapp.SpringWebApp.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Long> {
}
