package adfer.springapp.SpringWebApp.controller;

import adfer.springapp.SpringWebApp.repositories.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/authors")//path in localhost
    public String getBooks(Model model){
        model.addAttribute("authors", authorRepository.findAll());
        return "authors/list";
    }
}
