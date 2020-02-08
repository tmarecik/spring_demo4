package com.example.demo4;


import com.example.demo4.model.SkierowanieDoLekarza;
import com.example.demo4.model.User;
import com.example.demo4.service.SkierowanieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/skierowanie")
public class SkierowanieController {

    public class NotFoundException extends RuntimeException{};

    @Autowired
    SkierowanieServices sklService;

    @GetMapping("/listSkierowanie")
    public String listSkierowanie(Model model){
        model.addAttribute("skls", sklService.listSkierowanie());
        return "list-skierowanie-view";
    }

    @GetMapping("/addSkierowanie")
    public String addSkierowanie(Model model){
        model.addAttribute("skl", new SkierowanieDoLekarza());
        return "add-skierowanie";
    }


    @GetMapping("/getSkierowanie")
    public String getSkierowanie(@PathVariable String id, Model model){
        int idInt = Integer.parseInt(id);
        SkierowanieDoLekarza skl = sklService.getSkierowanie(idInt);
        if(skl == null) {
            throw new NotFoundException();
        }else {
            model.addAttribute("skl", skl);
            return "skl-details";
        }
    }

    @PostMapping("/addSkierowanie")
    public String createSkierowanie(@ModelAttribute SkierowanieDoLekarza skl, BindingResult bindingResult, Model model){
        sklService.createSkierowanie(skl.getLekarz(), skl.getPacjent(), skl.getTermin());
        return "redirect:/skierowanie/listSkierowanie";
    }


}
