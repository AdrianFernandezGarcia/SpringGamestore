package adfer.springapp.SpringWebApp.controller;

import adfer.springapp.SpringWebApp.model.Game;
import adfer.springapp.SpringWebApp.model.Store;
import adfer.springapp.SpringWebApp.repositories.GameRepository;
import adfer.springapp.SpringWebApp.repositories.PlatformRepository;
import adfer.springapp.SpringWebApp.repositories.PublisherRepository;
import adfer.springapp.SpringWebApp.repositories.StoreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
public class GameController {
    private final GameRepository gameRepository;
    private final PlatformRepository platformRepository;
    private final PublisherRepository publisherRepository;
    private final StoreRepository storeRepository;
    public GameController(GameRepository gameRepository, PlatformRepository platformRepository, PublisherRepository publisherRepository, StoreRepository storeRepository) {
        this.gameRepository = gameRepository;
        this.platformRepository = platformRepository;
        this.publisherRepository = publisherRepository;
        this.storeRepository = storeRepository;
    }

    @GetMapping("/stores/store/{storeId}/games")
    public String getGames(Model model,@PathVariable("storeId") Long storeId){
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("selectedStore",storeRepository.findById(storeId).get());
        return "games/list";
    }

    @GetMapping (value = "/stores/store/{storeId}/addGame")
    public String addGame(Model model, @PathVariable("storeId") Long storeId){
        model.addAttribute("newGame", new Game());
        model.addAttribute("selectedStore", storeRepository.findById(storeId).get());
        model.addAttribute("platformList", platformRepository.findAll());
        model.addAttribute("publisherList", publisherRepository.findAll());
        return "games/newGameForm";
    }

    @PostMapping (value = "/stores/store/{storeId}/games/save")
    public String saveGame(Game game,@PathVariable("storeId") Long storeId){
        Store store = storeRepository.findById(storeId).get();
        store.getGames().add(game);
        game.getStores().add(store);
        gameRepository.save(game);
        storeRepository.save(store);
        return "redirect:/stores/store/"+storeId;
    }

}
