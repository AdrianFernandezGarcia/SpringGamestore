package adfer.springapp.SpringWebApp.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int units;
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    public Stock() {
        this.units=0;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    //GETTERS AND SETTERS
    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    //EQUALS AND HASH
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stock stock = (Stock) o;

        return Objects.equals(game.getId(), stock.game.getId()) && Objects.equals(store.getId(),stock.store.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    //TO_STRING
    @Override
    public String toString() {
        return Integer.toString(this.units);
    }
}
