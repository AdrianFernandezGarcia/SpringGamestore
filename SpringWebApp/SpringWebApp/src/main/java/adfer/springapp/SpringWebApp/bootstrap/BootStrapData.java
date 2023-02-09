package adfer.springapp.SpringWebApp.bootstrap;

import adfer.springapp.SpringWebApp.repositories.LoginRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final LoginRepository loginRepository;

    public BootStrapData(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        loginRepository.deleteAll();
    }
}
