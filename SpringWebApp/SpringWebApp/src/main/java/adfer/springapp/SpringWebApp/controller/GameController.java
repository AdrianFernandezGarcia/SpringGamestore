package adfer.springapp.SpringWebApp.controller;

import adfer.springapp.SpringWebApp.model.Game;
import adfer.springapp.SpringWebApp.repositories.GameRepository;
import adfer.springapp.SpringWebApp.repositories.PlatformRepository;
import adfer.springapp.SpringWebApp.repositories.PublisherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
public class GameController {
    private final GameRepository gameRepository;
    private final PlatformRepository platformRepository;
    private final PublisherRepository publisherRepository;
    public GameController(GameRepository gameRepository,PlatformRepository platformRepository, PublisherRepository publisherRepository) {
        this.gameRepository = gameRepository;
        this.platformRepository = platformRepository;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/games")
    public String homeGames(){
        return "games/games";
    }

    @GetMapping("/games/list")
    public String getGames(Model model){
        model.addAttribute("games", gameRepository.findAll());
        return "games/list";
    }

    @GetMapping (value = "/games/addGame")
    public String addGame(Model model){
        model.addAttribute("newGame", new Game());
        model.addAttribute("platformList", platformRepository.findAll());
        model.addAttribute("publisherList", publisherRepository.findAll());
        return "games/newGameForm";
    }

    @PostMapping (value = "/games/save")
    public String saveGame(Game game){
        gameRepository.save(game);
        return "redirect:/games/list";
    }

}
