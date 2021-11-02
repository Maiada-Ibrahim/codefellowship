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
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class AppUserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
//        @Autowired
//        BCryptPasswordEncoder encode;
    @Autowired
PasswordEncoder encoder;

//
//    @GetMapping("/")
//    public String home(){
//        return ("home.html");
//    }

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
    @GetMapping("/")
    public String getHomePage(Principal principalgetdata, Model model){
        if(principalgetdata==null){
            return "error.html";
        } else {
            model.addAttribute("username", principalgetdata.getName());
            return "home.html";
        }
    }
@GetMapping("/profile")
public String myprofile(Model model, Principal principal) {
    ApplicationUser user = userRepository.findByUsername(principal.getName());
model.addAttribute("user",user);
    model.addAttribute("username", user.getUsername());
    model.addAttribute("id", user.getId());
    model.addAttribute("firstName", user.getFirstName());
    model.addAttribute("lastName", user.getLastName());
    model.addAttribute("dateOfBirth", user.getDateOfBirth());
    model.addAttribute("bio", user.getBio());
    return "profile";
}

    @PostMapping("/profile")
    public RedirectView postprofile(Principal p, @RequestParam String body){
        Post newpost= new Post(body,userRepository.findByUsername(p.getName()));
        postRepository.save(newpost);
        return new RedirectView("/profile");
    }
}
