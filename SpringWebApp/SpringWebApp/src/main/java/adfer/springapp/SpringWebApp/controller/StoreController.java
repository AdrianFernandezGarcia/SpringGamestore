package adfer.springapp.SpringWebApp.controller;

import adfer.springapp.SpringWebApp.model.Game;
import adfer.springapp.SpringWebApp.model.Store;
import adfer.springapp.SpringWebApp.repositories.EmployeeRepository;
import adfer.springapp.SpringWebApp.repositories.GameRepository;
import adfer.springapp.SpringWebApp.repositories.StoreRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class StoreController {
    private final StoreRepository storeRepository;
    public StoreController(StoreRepository storeRepository, EmployeeRepository employeeRepository, GameRepository gameRepository) {
        this.storeRepository = storeRepository;
    }

    @GetMapping("/stores")
    public String getStores(Model model){
        model.addAttribute("storeList", storeRepository.findAll());
        return "/stores/home";
    }

    @GetMapping("stores/addStore")
    public String showNewStoreForm(Model model){
        model.addAttribute("newStore",new Store());
        return "/stores/newStoreForm";
    }

    @PostMapping("/stores/save")
    public String saveStore(Store store){
        storeRepository.save(store);
        return "redirect:/stores";
    }

    //Get the specific store page, using @PathVariable and the id of the selected store
    @GetMapping("/stores/store/{id}")//id is the variable path that we map below to a Long data type
    public String getStore(Model model, @PathVariable("id") Long id){
        Store store = storeRepository.findById(id).get();
        Set<Game> storeGames = store.getGames();
        List<Integer> gameStocks = new ArrayList<>();

        storeGames.forEach(game -> game.getStocks().stream().filter(stock -> stock.getStore().getId() == id).forEach(stock -> gameStocks.add(stock.getUnits())));

        model.addAttribute("selectedStore", store);
        model.addAttribute("gameStocks", gameStocks);

        return "/stores/store";
    }


}
