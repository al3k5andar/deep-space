package openspace.spacepicture.controllers;

import openspace.spacepicture.api.v1.model.ItemDto;
import openspace.spacepicture.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @GetMapping("/showYesterdayImage")
    public String showTwoImages(Model model){

        model.addAttribute("item", service.yesterdayImage());
        return "show_image";
    }

    @PostMapping("/getInRange")
    public String showFourImages(Model model,
                                 @RequestParam("start") String start,
                                 @RequestParam("end") String end){
        List<ItemDto> items= service.getInRange(start, end);
        model.addAttribute("collection", items);

        return "show_multiple_images";
    }

    @GetMapping("/form")
    public String showForm(){

        return "search_form";
    }

    @PostMapping("/getByDate")
    public String processByDateForm(@RequestParam("inputDate") String inputDate, Model model){
        model.addAttribute("item", service.getByDate(inputDate));
        return "show_image";
    }

    @GetMapping("/aboutMe")
    public String showAboutMe(Model model){
//        coming soon
        return "about_me";
    }

}
