package adfer.springapp.SpringWebApp.controller;

import adfer.springapp.SpringWebApp.model.Employee;
import adfer.springapp.SpringWebApp.model.Store;
import adfer.springapp.SpringWebApp.repositories.EmployeeRepository;
import adfer.springapp.SpringWebApp.repositories.StoreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StoreController {
    private final StoreRepository storeRepository;
    public StoreController(StoreRepository storeRepository, EmployeeRepository employeeRepository) {
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
        model.addAttribute("selectedStore", storeRepository.findById(id).get());
        return "/stores/store";
    }


}
