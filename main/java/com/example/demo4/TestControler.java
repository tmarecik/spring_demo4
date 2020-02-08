package com.example.demo4;

import com.example.demo4.model.User;
import com.example.demo4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestControler {


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

    @PostMapping("/addUser")
    public String createUser(@ModelAttribute User user, Model model){
        userService.createUser(user.getImie(), user.getNazwisko(), user.getWiek());
        return "redirect:/listUsers";
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
