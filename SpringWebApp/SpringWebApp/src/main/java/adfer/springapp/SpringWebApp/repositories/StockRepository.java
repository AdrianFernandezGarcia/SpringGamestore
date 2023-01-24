package adfer.springapp.SpringWebApp.repositories;

import adfer.springapp.SpringWebApp.model.Stock;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StockRepository extends CrudRepository<Stock, Long> {
    @EntityGraph(attributePaths = {"game" ,"store"})
    Optional<Stock> findById(Long id);
}
