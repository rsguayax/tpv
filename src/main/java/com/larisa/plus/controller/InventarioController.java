package com.larisa.plus.controller;

import com.larisa.plus.model.article.ArticleRepository;
import com.larisa.plus.model.articleEnterprise.ArticleEnterprise;
import com.larisa.plus.model.articleEnterprise.ArticleEnterpriseRepository;
import com.larisa.plus.model.articleProvider.ArticleProvider;
import com.larisa.plus.model.articleProvider.ArticleProviderRepository;
import com.larisa.plus.model.articleProviderSucursal.ArticleProviderSucursal;
import com.larisa.plus.model.articleProviderSucursal.ArticleProviderSucursalRepository;
import com.larisa.plus.model.catalog.CatalogRepository;
import com.larisa.plus.model.catalog.ItemCatalog;
import com.larisa.plus.model.catalog.ItemCatalogRepository;
import com.larisa.plus.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventarioController {

    @Autowired
    private ArticleRepository artRep;
    @Autowired
    private ArticleProviderRepository apRep;
    @Autowired
    private ArticleEnterpriseRepository aeRep;
    @Autowired
    private ArticleProviderSucursalRepository apsRep;
    @Autowired
    private CatalogRepository catRep;
    @Autowired
    private ItemCatalogRepository icRep;
    @Autowired
    private UserRepository usrRep;

    @GetMapping("/")
    public ModelAndView getListArticles(Model model){
        int idSuc = 1;
        List<ArticleProvider> articles = apsRep.getArticleProviderSucursal(idSuc);
        model.addAttribute("articlesProvider", articles);
        ModelAndView mv = new ModelAndView("inventory/list");
        return mv;
    }

}
