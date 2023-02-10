package adfer.springapp.SpringWebApp.controller;

import adfer.springapp.SpringWebApp.model.Platform;
import adfer.springapp.SpringWebApp.repositories.PlatformRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlatformController {

    private final PlatformRepository platformRepository;
    public PlatformController(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    @GetMapping("/platforms")//path in localhost
    public String getPlatform(Model model){
        model.addAttribute("platforms", platformRepository.findAll());

        return "platforms/list";
    }

    @GetMapping (value = "/platforms/addPlatform")
    public String addPlatform(Model model){
        model.addAttribute("newPlatform", new Platform());

        return "platforms/newPlatformForm";
    }

    @PostMapping(value = "/platforms/save")
    public String savePlatform(Platform platform){
        platformRepository.save(platform);

        return "platforms/list";
    }


}
