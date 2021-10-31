package com.example.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppUserController {
    @Autowired
    UserRepository userRepository;

//        @Autowired
//        BCryptPasswordEncoder encode;
    @Autowired
PasswordEncoder encoder;


    @GetMapping("/")
    public String home(){
        return ("home.html");
    }

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpUser(@RequestParam String username, @RequestParam String password){
        ApplicationUser appUser = new ApplicationUser(username, encoder.encode(password));
        userRepository.save(appUser);
        return "login";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

@GetMapping("/users/{id}")
public String profile(@PathVariable("id") int id, Model m){
    ApplicationUser user  = userRepository.findById(id).get();
    m.addAttribute("data",user);
    return "profile.html";
}
    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }
}
