package com.larisa.plus.controller;

import com.larisa.plus.model.article.ArticleRepository;
import com.larisa.plus.model.buy.Buy;
import com.larisa.plus.model.buy.BuyRepository;
import com.larisa.plus.model.catalog.CatalogRepository;
import com.larisa.plus.model.catalog.ItemCatalogRepository;
import com.larisa.plus.model.user.UserRepository;
import com.larisa.plus.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/buy")
public class BuyController {

    @Autowired
    private ArticleRepository artRep;
    @Autowired
    private CatalogRepository catRep;
    @Autowired
    private ItemCatalogRepository icRep;
    @Autowired
    private UserRepository usrRep;
    @Autowired
    private BuyRepository byRep;

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy");

    @GetMapping("/list")
    public ModelAndView getList(Model model){
        int idSuc = 1;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("user", userDetails );

        List<Buy> buys = byRep.getBuyBySucursal_Id(idSuc);
        model.addAttribute("buys", buys);
        ModelAndView mv = new ModelAndView("buy/list");

        model.addAttribute("date", sdf.format(new Date()));
        return mv;
    }

    @GetMapping("/{buy_id}")
    public ModelAndView getBuy(Model model, @PathVariable("buy_id") int id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("user", userDetails );

        Buy buy = byRep.getBuyById(id);
        model.addAttribute("type_document", icRep.findByCatalog_Id(3) );/*types of documents*/
        model.addAttribute("status_buy", icRep.findByCatalog_Id(2));/*status of buy*/
        model.addAttribute("buy", buy);
        model.addAttribute("date", sdf.format(new Date()));
        return new ModelAndView("buy/detail");
    }

    @PostMapping("/save")
    public String save(){
        return "redirect:/buy/list";
    }

}
