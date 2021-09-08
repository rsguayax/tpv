package com.larisa.plus.controller;

import com.larisa.plus.model.article.ArticleRepository;
import com.larisa.plus.model.catalog.CatalogRepository;
import com.larisa.plus.model.catalog.ItemCatalogRepository;
import com.larisa.plus.model.user.UserEnterprise;
import com.larisa.plus.model.user.UserEnterpriseRepository;
import com.larisa.plus.model.user.UserRepository;
import com.larisa.plus.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private ArticleRepository artRep;
    @Autowired
    private CatalogRepository catRep;
    @Autowired
    private ItemCatalogRepository icRep;
    @Autowired
    private UserRepository usrRep;
    @Autowired
    private UserEnterpriseRepository uenRep;

    @GetMapping("/")
    public ModelAndView getListArticles(Model model){
        int idSuc = 1;
        //List<ArticleProvider> articles = apsRep.getArticleProviderSucursal(idSuc);
        //model.addAttribute("articlesProvider", articles);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("user",userDetails);
        ModelAndView mv = new ModelAndView("sale/billing");
        int enterprise_id = 1;//LAVADORA EL REY
        UserEnterprise ue = uenRep.findByUser_IdAndEnterprise_Id(userDetails.getId(), enterprise_id);
        model.addAttribute("ue", ue);
        return mv;
    }

}
