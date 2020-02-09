package com.example.demo4.controller;

import com.example.demo4.model.User;
import com.example.demo4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestControler {

    public static class NotFoundException extends RuntimeException{};

    @Autowired                  //wstrzykujemy service
    UserService userService;

    @GetMapping("/listUsers")
    public String test(Model model) {
//        User user = new User(13, "Tomasz", "AAA", 24);
//        List<User> users = new ArrayList<>();
//        users.add(new User(11, "Jan", "BBB", 26));
//        users.add(new User(12, "Sebastain", "CCC", 27));
//        users.add(new User(14, "Bah", "DDD", 28));
//        users.add(new User(15, "Jan", "EEE", 29));
//        model.addAttribute("user", user);
//        model.addAttribute("users", users);
        model.addAttribute("users", userService.listUsers()); //w tym momencie odnosimy sie do naszego servic'u
        return "list-users-view";
    }

    @GetMapping("/addUsers")
    public String addUser(Model model){
        model.addAttribute("user", new User());  //to jest szblon usera
        return "add-user";
    }

    @GetMapping("/getUser/{id}")
    public String getUser(@PathVariable String id, Model model){

        int id1 = Integer.parseInt(id);

        User user = userService.getUser(id1);
        if(user == null){
            throw new NotFoundException();
        } else {
            model.addAttribute("user", user);
            return "user-details";
        }
    }

    @PostMapping("/addUser")
    public String createUser(@ModelAttribute User user, BindingResult bindingResult, Model model) {
        validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "add-user";
        } else {
            userService.createUser(user.getImie(), user.getNazwisko(), user.getWiek());
            return "redirect:/listUsers";
        }
    }


    @ExceptionHandler(NotFoundException.class)            //obsługa wyjątków
    public String notFound(){
        return "404";
    }

    @ExceptionHandler(NumberFormatException.class)            //obsługa wyjątków
    public String wrongFormatID(){
        return "numb-format-exep";
    }


    public void validate(User user, BindingResult bindingResult){
        if(user.getImie() == null || user.getImie().isEmpty()){
            bindingResult.addError(new ObjectError("imie", "Musisz podać imie"));
        }

        if(user.getNazwisko() == null || user.getNazwisko().isEmpty()){
            bindingResult.addError(new ObjectError("nazwisko", "Musisz podac nazwisko"));
        }

        if(user.getWiek() < 0){
            bindingResult.addError(new ObjectError("wiek", " wiek nie moze być < 0"));
        }


    }



//        @GetMapping("/test")
//    public String test(Model model) {     //tutaj dodaliśmy model który jest przetwarzany przez "thymeleaf"
//        model.addAttribute("imie", "Tomasz");
//        model.addAttribute("styl", "color: red");
//        return "test-app";


    /*
    * ta funkcja przyjmuje widok o nazwie test-app
    * */
//    @GetMapping("/test")
//    public String test() {
//        return "test-app";


//    }

}
