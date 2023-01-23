package adfer.springapp.SpringWebApp.controller;

import adfer.springapp.SpringWebApp.model.Publisher;
import adfer.springapp.SpringWebApp.repositories.PublisherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublisherController {
    private final PublisherRepository publisherRepository;

    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/publishers")
    public String homePublishers(){
        return "publishers/publishers";
    }

    @GetMapping("/publishers/list")
    public String getPublishers(Model model){
        model.addAttribute("publisherList", publisherRepository.findAll());
        return "/publishers/list";
    }

    @GetMapping("/publishers/addPublisher")
    public String showNewPublisherForm(Model model){
        model.addAttribute("newPublisher", new Publisher());
        return "/publishers/newPublisherForm";
    }

    @PostMapping("/publishers/save")
    public String savePublisher(Publisher publisher){
        publisherRepository.save(publisher);
        return "redirect:/publishers/list";
    }


}



