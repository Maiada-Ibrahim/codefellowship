package com.example.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import java.util.ArrayList;
import java.util.List;

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
    public RedirectView newUser(@RequestParam(value="username") String username,@RequestParam(value="password") String password,@RequestParam(value="firstName") String firstName,@RequestParam(value="lastName") String lastName,@RequestParam(value="dateOfBirth") String dateOfBirth,@RequestParam(value="bio") String bio  ){
        ApplicationUser newUser = new ApplicationUser(username,encoder.encode(password),firstName,lastName,dateOfBirth,bio);
        newUser=userRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser,null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/profile");
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
//    model.addAttribute("username", user.getUsername());
//    model.addAttribute("id", user.getId());
//    model.addAttribute("firstName", user.getFirstName());
//    model.addAttribute("lastName", user.getLastName());
//    model.addAttribute("dateOfBirth", user.getDateOfBirth());
//    model.addAttribute("bio", user.getBio());
    return "profile";
}

    @PostMapping("/profile")
    public RedirectView postprofile(Principal p, @RequestParam String body){
        Post newpost= new Post(body,userRepository.findByUsername(p.getName()));
        postRepository.save(newpost);
        return new RedirectView("/profile");
    }
    @GetMapping ("/suggest")
    public String home(Model m, Principal p){
        List<ApplicationUser> allUsers= userRepository.findAll();
        ApplicationUser currentUser= userRepository.findByUsername(p.getName());
        List<ApplicationUser> followingList= currentUser.getFollowing();
        List<ApplicationUser> notFollowedUser= new ArrayList<>();
        allUsers.forEach((element)->{
            if ((! followingList.contains(element)) && element.getUsername() != currentUser.getUsername() ){
                notFollowedUser.add(element);
            }
        });
        m.addAttribute("users",notFollowedUser);
        return ("suggest.html");
    }
    @GetMapping("/follow/{id}")
    public RedirectView addFollower(@PathVariable("id") int id, Principal p){
        ApplicationUser usertoFollow = userRepository.findById(id).get();
        ApplicationUser currentUser = userRepository.findByUsername(p.getName());
        currentUser.setFollowing(usertoFollow);
        userRepository.save(currentUser);
        return new RedirectView("/suggest");
    }
    @GetMapping("/followList")
    public String followerContent( Principal p, Model m){
        ApplicationUser currentUser = userRepository.findByUsername(p.getName());
        List<ApplicationUser> followList= currentUser.getFollowing();
        m.addAttribute("followings",followList);
        return "feed.html";
    }

    @GetMapping("/profile/{id}")
    public String profileOfFollowing(@PathVariable("id") int id, Model m){
        ApplicationUser following = userRepository.findById(id).get();
        m.addAttribute("user",following);
        return ("profile.html");
    }
}
