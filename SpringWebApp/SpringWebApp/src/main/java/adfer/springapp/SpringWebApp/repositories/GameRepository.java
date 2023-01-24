package adfer.springapp.SpringWebApp.repositories;
import adfer.springapp.SpringWebApp.model.Game;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameRepository extends CrudRepository<Game,Long> {
    @EntityGraph(attributePaths = {"platforms" ,"publisher", "stores","stocks" })
    Optional<Game> findById(Long id);
}
