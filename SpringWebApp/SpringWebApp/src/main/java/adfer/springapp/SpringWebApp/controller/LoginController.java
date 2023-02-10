package adfer.springapp.SpringWebApp.controller;

import adfer.springapp.SpringWebApp.model.Employee;
import adfer.springapp.SpringWebApp.model.Login;
import adfer.springapp.SpringWebApp.repositories.EmployeeRepository;
import adfer.springapp.SpringWebApp.repositories.LoginRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class LoginController {

    private final EmployeeRepository employeeRepository;
    private final LoginRepository loginRepository;
    private List<String> accessPages= new ArrayList<>();

    public LoginController(EmployeeRepository employeeRepository, LoginRepository loginRepository) {
        this.employeeRepository = employeeRepository;
        this.loginRepository = loginRepository;
    }
    @GetMapping("/login")
    public String redirectToLogin(){
        return "login";
    }
    @PostMapping("/login/get")
    public String getLogin(@RequestParam String email, @RequestParam String pass, HttpSession session){
        for (Employee employee : employeeRepository.findAll()) {
            if (employee.getEmail().equals(email) && employee.getPassword().equals(pass)){
                Login newLogin = new Login(employee);
                loginRepository.save(newLogin);
                session.setAttribute("employee", employee);
                initMap(employee);
                session.setAttribute("pagesList",accessPages);
            }
        }

        return "login";
    }

    /**
     * Method that initializes the list of the pages that can be accessed by each role.
     * The first position of each list represents the starting page of each role.
     * @param currentEmployee the employee that has logged in
     */
    private void initMap(Employee currentEmployee){
        String storesUrl="/stores/store/" + currentEmployee.getStore().getId();

        String[] staffPages = { storesUrl + "/games", storesUrl + "/addGame"};

        String[] managerPages = { storesUrl
                ,storesUrl + "/games", storesUrl + "/addGame",storesUrl + "/games/save"
                ,storesUrl + "/employees", storesUrl + "/addEmployee",storesUrl + "/employees/save"};

        String[] adminPages = { "/index"
                ,"/platforms","/platforms/addPlatform","/platforms/save"
                ,"/games","/games/addGame","/games/save"
                ,"/publishers","/publishers/addPublisher","/publishers/save"
                ,"/stores","/stores/addStore","/stores/save"};

        switch (currentEmployee.getRole().name()) {
            case "STAFF" -> accessPages= Arrays.stream(staffPages).toList();
            case "MANAGER" -> accessPages= Arrays.stream(managerPages).toList();
            case "ADMIN" -> accessPages= Arrays.stream(adminPages).toList();
        }
    }

}
