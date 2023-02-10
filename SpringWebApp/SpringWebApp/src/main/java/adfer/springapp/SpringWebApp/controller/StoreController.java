package adfer.springapp.SpringWebApp.controller;

import adfer.springapp.SpringWebApp.model.Employee;
import adfer.springapp.SpringWebApp.model.Store;
import adfer.springapp.SpringWebApp.repositories.StoreRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Controller
public class StoreController {
    private final StoreRepository storeRepository;
    public StoreController(StoreRepository storeRepository) {
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

        return "/stores/home";
    }

    //Get the specific store page, using @PathVariable and the id of the selected store
    @GetMapping("/stores/store/{id}")//id is the variable path that we map below to a Long data type
    public String getStore(Model model, @PathVariable("id") Long id, HttpSession session){
        model.addAttribute("selectedStore", storeRepository.findById(id).get());
        model.addAttribute("currentEmployee", (Employee) session.getAttribute("employee"));

        return "/stores/store";
    }

    @GetMapping("/stores/store/{id}/employees")//id is the variable path that we map below to a Long data type
    public String getStoreEmployees(Model model,HttpSession session, @PathVariable("id") Long id){
        model.addAttribute("selectedStore", storeRepository.findById(id).get());
        model.addAttribute("currentEmployee", (Employee) session.getAttribute("employee") );

        return "/stores/storeEmployees";
    }

    @GetMapping("/stores/store/{id}/games")//id is the variable path that we map below to a Long data type
    public String getStoreGames(Model model, HttpSession session ,@PathVariable("id") Long id){
        Store store = storeRepository.findById(id).get();
        model.addAttribute("selectedStore", store);
        model.addAttribute("currentEmployee", (Employee) session.getAttribute("employee") );

        return "/stores/storeGames";
    }


}
