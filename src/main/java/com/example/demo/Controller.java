package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public String indexpage(Model model){
        model.addAttribute("users",new UserEntity());

        return "index";
    }

    @GetMapping("/users")
    public String userspage(Model model){
        model.addAttribute("allusers",userRepository.findAll());
        return "users";
    }

    @PostMapping("/insert")
    public String insertformdata(UserEntity users){
        userRepository.save(users);
        return "success";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editstud(@PathVariable (name = "id") Long id){
        ModelAndView  modelAndView = new ModelAndView("index");
        UserEntity user = userRepository.getById(id);
        modelAndView.addObject("users",user);

        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public String  deleteuser(@PathVariable (name = "id") Long id){
        userRepository.deleteById(id);
        return "success";
    }

}
