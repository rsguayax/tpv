package com.larisa.plus.controller;

import com.larisa.plus.model.user.UserRepository;
import com.larisa.plus.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {

    @Autowired
    private UserRepository userRep;

    @GetMapping("/")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView loginOpcion2(){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @GetMapping("/home")
    public ModelAndView createUserTest(Model model){
        //User user = new User("Richar","Guaya","rsguayax@gmail.com","rsguayax","1234", "/img/user-profile.png");
        //userRep.save(user);
        System.out.println("entró a rest /home");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("user",userDetails);
        ModelAndView mv = new ModelAndView("index/home");
        return mv;
    }
}
