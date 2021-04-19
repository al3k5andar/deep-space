package openspace.spacepicture.controllers;

import openspace.spacepicture.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController
{
    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String showIndex(){
        return "index";
    }

    @GetMapping("/todayImage")
    public String todayImage(Model model){
        model.addAttribute("item", service.todayImage());

        return "show_image";
    }

}
