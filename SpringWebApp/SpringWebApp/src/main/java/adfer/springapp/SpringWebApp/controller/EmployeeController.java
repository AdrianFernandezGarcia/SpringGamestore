package adfer.springapp.SpringWebApp.controller;

import adfer.springapp.SpringWebApp.model.Employee;
import adfer.springapp.SpringWebApp.repositories.EmployeeRepository;
import adfer.springapp.SpringWebApp.repositories.StoreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@SuppressWarnings("OptionalGetWithoutIsPresent")
@Controller
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final StoreRepository storeRepository;

    public EmployeeController(EmployeeRepository employeeRepository, StoreRepository storeRepository) {
        this.employeeRepository = employeeRepository;
        this.storeRepository = storeRepository;
    }

    @GetMapping("stores/store/{id}/addEmployee")
    //{id} is the id of the store where the employee is going to be added
    public String showNewEmployeeForm(Model model, @PathVariable("id") Long id){
        model.addAttribute("roles",Employee.Role.values());
        model.addAttribute("newEmployee", new Employee());
        model.addAttribute("employee_store", storeRepository.findById(id).get());
        return "stores/newEmployeeForm";
    }

    @PostMapping("stores/store/{storeId}/saveEmployee")
    //IMPORTANT: if u write 'id' on url it will detect that it´s the employee id instead of the store´s
    public String saveEmployee(Employee employee, @PathVariable("storeId") Long storeId){
        employee.setStore(storeRepository.findById(storeId).get());
        employee.setEmail(employee.getFirstname().toLowerCase()+"."+employee.getLastname().toLowerCase()+"@gamestore.com");
        employee.setPassword("gamestore"+ employee.getRole().name().toLowerCase());
        employeeRepository.save(employee);
        return "stores/storeEmployees";
    }
}
