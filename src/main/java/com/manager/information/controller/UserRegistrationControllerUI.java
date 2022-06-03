package com.manager.information.controller;

import com.manager.information.domain.TitleType;
import com.manager.information.domain.User;

import com.manager.information.repository.UserRepository;
import com.manager.information.webData.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserRegistrationControllerUI {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
    @GetMapping("/viewUserRegister")
    public String getUserRegistration(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("ETitle", TitleType.values());
       return "user/DisplayAllUserRegistration";


    }

    @RequestMapping("/userRegistration")
    // /userRegistration: API : application programming  Interface / API should test in postman and swagger
    public String createUserRegistration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("ETitleType", TitleType.values());
       // return "user/newUserRegistration";
        return "registration";

    }

    @PostMapping("/process_register")
    public String processRegister(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "register_successfully";

    }

}







