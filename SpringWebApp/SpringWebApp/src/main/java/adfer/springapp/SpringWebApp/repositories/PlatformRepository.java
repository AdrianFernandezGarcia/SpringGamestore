package adfer.springapp.SpringWebApp.repositories;

import adfer.springapp.SpringWebApp.model.Platform;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional
public interface PlatformRepository extends CrudRepository<Platform,Long> {
    @EntityGraph(attributePaths = "games")
    Optional<Platform> findById(Long id);
}
