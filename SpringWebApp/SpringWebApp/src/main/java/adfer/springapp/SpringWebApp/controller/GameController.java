package adfer.springapp.SpringWebApp.controller;

import adfer.springapp.SpringWebApp.model.Employee;
import adfer.springapp.SpringWebApp.model.Game;
import adfer.springapp.SpringWebApp.model.Stock;
import adfer.springapp.SpringWebApp.model.Store;
import adfer.springapp.SpringWebApp.repositories.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Iterator;

@Controller()
public class GameController {
    private final GameRepository gameRepository;
    private final PlatformRepository platformRepository;
    private final PublisherRepository publisherRepository;
    private final StoreRepository storeRepository;
    private final StockRepository stockRepository;
    public GameController(GameRepository gameRepository, PlatformRepository platformRepository, PublisherRepository publisherRepository, StoreRepository storeRepository, StockRepository stockRepository) {
        this.gameRepository = gameRepository;
        this.platformRepository = platformRepository;
        this.publisherRepository = publisherRepository;
        this.storeRepository = storeRepository;
        this.stockRepository = stockRepository;
    }
    @GetMapping("/games")
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
    public String saveNewGame(Game game){
        gameRepository.save(game);

        return "games/list";
    }

    @GetMapping (value = "/stores/store/{storeId}/addGame")
    public String showAddGameForm(Model model, HttpSession session, @PathVariable("storeId") Long storeId){
        model.addAttribute("currentEmployee", (Employee) session.getAttribute("employee") );
        model.addAttribute("existingGames", gameRepository.findAll());
        model.addAttribute("selectedStore", storeRepository.findById(storeId).get());
        model.addAttribute("stock", new Stock());

        return "stores/addGameForm";
    }

    /**
     * Saves the game to the store list and the stock for that game (if not added before)
     *
     * @param storeId       the id of the current store
     * @param selectedGame  the game to be added to the store
     * @param selectedStock the stock to be set to that game in that store
     * @return url back to the game list
     */
    @PostMapping (value = "/stores/store/{storeId}/games/save")//Get the selected game with @ModelAttribute (mapping it to a Game object), adding into the store list and saving it.
    public String addGameToStore(@PathVariable("storeId") Long storeId, @ModelAttribute("existingGames") Game selectedGame, @ModelAttribute("stock") Stock selectedStock) {
        boolean gameAdded=false;
        Store selectedStore = storeRepository.findById(storeId).get();
        Iterator<Stock> stockIterator = stockRepository.findAll().iterator();

        while(stockIterator.hasNext() && !gameAdded){
            Stock stock = stockIterator.next();
            if(stock.getGame().equals(selectedGame) && stock.getStore().equals(selectedStore)){
                stock.setUnits(selectedStock.getUnits());
                stockRepository.save(stock);
                gameAdded=true;
            }
        }

        if(!gameAdded){
            selectedStock.setGame(selectedGame);
            selectedStock.setStore(selectedStore);
            stockRepository.save(selectedStock);
            selectedGame.getStocks().add(selectedStock);
            selectedStore.getStocks().add(selectedStock);
            selectedStore.getGames().add(selectedGame);
            selectedGame.getStores().add(selectedStore);
            storeRepository.save(selectedStore);
            gameRepository.save(selectedGame);
        }

        return "stores/storeGames";
    }

}
