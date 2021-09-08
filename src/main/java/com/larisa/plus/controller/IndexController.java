package com.larisa.plus.controller;

import com.larisa.plus.model.enterprise.Enterprise;
import com.larisa.plus.model.enterprise.EnterpriseRepository;
import com.larisa.plus.model.user.UserEnterprise;
import com.larisa.plus.model.user.UserEnterpriseRepository;
import com.larisa.plus.model.user.UserRepository;
import com.larisa.plus.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@RestController
public class IndexController {
    @Autowired
    private EnterpriseRepository entRep;
    @Autowired
    private UserRepository userRep;
    @Autowired
    private UserEnterpriseRepository uenRep;


    @RequestMapping("/")
    public ModelAndView login(Model model){
        String code = "emplus";
        System.out.println("código recibido: "+ code);
        Enterprise enterprise = entRep.findByCode(code);
        System.out.println("enterprise: " + enterprise);
        model.addAttribute("enterprise", enterprise);
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView loginOpcion2(Model model){
        String code = "emplus";
        Enterprise enterprise = entRep.findByCode(code);
        System.out.println("enterprise: " + enterprise);
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @GetMapping("/logout")
    public ModelAndView login(HttpServletRequest req) {
        HttpSession sesion = req.getSession(true);
        sesion.invalidate();
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("login"); // resources/template/login.html
        return modelView;
    }

    @GetMapping("/home")
    public ModelAndView createUserTest(Model model){
        //User user = new User("Richar","Guaya","rsguayax@gmail.com","rsguayax","1234", "/img/user-profile.png");
        //userRep.save(user);
        System.out.println("entró a rest /home");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("user",userDetails);
        int enterprise_id = 1;//LAVADORA EL REY
        UserEnterprise ue = uenRep.findByUser_IdAndEnterprise_Id(userDetails.getId(), enterprise_id);
        model.addAttribute("ue", ue);
        ModelAndView mv = new ModelAndView("index/home");
        return mv;
    }
}
