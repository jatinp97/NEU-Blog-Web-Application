package com.neublog.neublogwebapp.controller;

import com.neublog.neublogwebapp.dto.RegistrationDto;
import com.neublog.neublogwebapp.entity.User;
import com.neublog.neublogwebapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    //handler method to handle login page request
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    //handler method to handle the User Sign UP request

    @GetMapping("/register")
    public String showRegistrationForm(Model model){

        //To hold the Form Data
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user",user);

        return "/register";
    }

    //handler method to handle the User Registration Form Submission Request

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result,
                           Model model){
        User existingUser = userService.findByEmail(user.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email",null,"User Already exists with same EmailID");
        }

        if(result.hasErrors()){
            model.addAttribute("user",user);
            return "/register";
        }
        userService.saveUser(user);

        return "redirect:/register?success";
    }

}
