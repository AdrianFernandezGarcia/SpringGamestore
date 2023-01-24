package adfer.springapp.SpringWebApp.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String city;
    private String country;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Set<Stock> stocks= new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")//Creates a column store_id in employee table
    private Set<Employee> employees= new HashSet<>();
    @ManyToMany(mappedBy = "stores", fetch = FetchType.LAZY)
    private Set<Game> games= new HashSet<>();
    public Store() {
    }

    public Store(String address, String city, String country, Set<Stock> stocks, Set<Employee> employees, Set<Game> games) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.stocks = stocks;
        this.employees = employees;
        this.games = games;
    }

    //GETTERS AND SETTERS
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    //EQUALS AND HASH
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Store store = (Store) o;

        return Objects.equals(id, store.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    //TO_STRING
    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", employees=" + employees +
                ", games=" + games +
                '}';
    }
}
