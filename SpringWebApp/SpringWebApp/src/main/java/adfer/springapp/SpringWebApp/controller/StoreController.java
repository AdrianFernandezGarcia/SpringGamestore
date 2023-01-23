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
    private final EmployeeRepository employeeRepository;

    public StoreController(StoreRepository storeRepository, EmployeeRepository employeeRepository) {
        this.storeRepository = storeRepository;
        this.employeeRepository = employeeRepository;
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
        return "redirect:/stores/list";
    }

    //Get the specific store page, using @PathVariable and the id of the selected store
    @GetMapping("/stores/store/{id}")//id is the variable path that we map below to a Long data type
    public String getStore(Model model, @PathVariable("id") Long id){
        model.addAttribute("selectedStore", storeRepository.findById(id).get());
        return "/stores/store";
    }

    @GetMapping("stores/store/{id}/addEmployee")
    //{id} is the id of the store where the employee is going to be added
    public String showNewEmployeeForm(Model model, @PathVariable("id") Long id){
        model.addAttribute("newEmployee", new Employee());
        model.addAttribute("employee_store", storeRepository.findById(id).get());
        return "stores/newEmployeeForm";
    }

    @PostMapping("stores/store/{storeId}/saveEmployee")
    //IMPORTANT: if u write 'id' on url it will detect that it´s the employee id instead of the store´s
    public String saveEmployee(Employee employee, @PathVariable("storeId") Long storeId){
        employee.setStore(storeRepository.findById(storeId).get());
        employeeRepository.save(employee);
        return"redirect:/stores/store/"+storeId;
    }


}
