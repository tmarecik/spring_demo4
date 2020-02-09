package com.example.demo4.controller;


import com.example.demo4.DTO.SkierowanieDoLekarzaDTO;
import com.example.demo4.service.SkierowanieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/skierowanie")
public class SkierowanieController {

    public class NotFoundException extends RuntimeException{};

    @Autowired
    SkierowanieServices sklService;

    @GetMapping("/list")
    public String listSkierowanie(Model model){
        model.addAttribute("skls", sklService.listSkierowanie());
        return "list-skierowanie-view";
    }

    @GetMapping("/{id}")
    public String getSkierowanie(@PathVariable Integer id, Model model){
        int idInt = id;
        SkierowanieDoLekarzaDTO skl = sklService.getSkierowanie(idInt);
        if(skl == null) {
            throw new NotFoundException();
        }else {
            model.addAttribute("skl", skl);
            return "skl-details";   //get-skierowanie
        }
    }

    @GetMapping("/dodaj")
    public String addSkierowanie(Model model){
        model.addAttribute("skl", new SkierowanieDoLekarzaDTO()); //tworzymy pusty obiekt  żeby themleave mógł na czymś działać
        return "add-skierowanie";
    }

    @PostMapping("/dodaj")
    public String createSkierowanie(
            @Valid                                          //to do validacji
            @ModelAttribute SkierowanieDoLekarzaDTO skl,
            BindingResult bindingResult,
            Model model){
        if(bindingResult.hasErrors()){                      //validacja jezeli ma error to zwróć urzytkownikowi żeby poprawił
            model.addAttribute("skl", skl);
            return "add-skierowanie";                       //wróć do tego widoku (formularza)
        }
        sklService.createSkierowanie(skl.getLekarz(), skl.getPacjent(), skl.getTermin());
        return "redirect:/skierowanie/list";
    }

    @GetMapping("/modyfikuj/{id}")
    public String modyfikulSierowanie(@PathVariable Integer id, Model model){
        model.addAttribute("skl", sklService.getSkierowanie(id));
        return "modyfikuj-skierowanie";
    }

    @PostMapping("/modyfikuj")
    public String updateSkierowanie(
            @Valid
            @ModelAttribute SkierowanieDoLekarzaDTO skierowanieDoLekarza,
            BindingResult bindingResult,
            Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("skl", skierowanieDoLekarza);
            return "modyfikuj-skierowanie";
        }
        sklService.updateSkierowanie(skierowanieDoLekarza);
        return String.format("redirect:/skierowanie/%d", skierowanieDoLekarza.getId());
    }

    @GetMapping("/usun/{id}")
    public String usunSkierowanie(@PathVariable Integer id){
        sklService.usunSkierowanie(id);
        return "redirect:/skierowanie/list";
    }

    @ExceptionHandler(TestControler.NotFoundException.class)            //obsługa wyjątków
    public String notFound(){
        return "404";
    }



}
