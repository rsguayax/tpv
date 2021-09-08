package com.larisa.plus.controller;

import com.larisa.plus.model.article.ArticleRepository;
import com.larisa.plus.model.buy.Buy;
import com.larisa.plus.model.buy.BuyRepository;
import com.larisa.plus.model.catalog.CatalogRepository;
import com.larisa.plus.model.catalog.ItemCatalogRepository;
import com.larisa.plus.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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


    @GetMapping("/list")
    public ModelAndView getList(Model model){
        int idSuc = 1;
        List<Buy> buys = byRep.getBuyBySucursal_Id(idSuc);
        model.addAttribute("buys", buys);
        ModelAndView mv = new ModelAndView("buy/list");
        return mv;
    }

    @GetMapping("/{buy_id}")
    public ModelAndView getBuy(Model model, @PathVariable("buy_id") int id){
        Buy buy = byRep.getBuyById(id);
        model.addAttribute("type_document", icRep.findByCatalog_Id(3) );/*types of documents*/
        model.addAttribute("status_buy", icRep.findByCatalog_Id(2));/*status of buy*/
        model.addAttribute("buy", buy);
        return new ModelAndView("buy/detail");
    }

    @PostMapping("/save")
    public String save(){
        return "redirect:/buy/list";
    }

}
