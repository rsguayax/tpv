package com.larisa.plus.controller;

import com.larisa.plus.model.article.Article;
import com.larisa.plus.model.article.ArticleRepository;
import com.larisa.plus.model.article.ArticleSucursal;
import com.larisa.plus.model.article.ArticleSucursalRepository;
import com.larisa.plus.model.article_vehicle.ArticleVehicle;
import com.larisa.plus.model.article_vehicle.ArticleVehicleRepository;
import com.larisa.plus.model.catalog.ItemCatalogRepository;
import com.larisa.plus.model.client.ClientRepository;
import com.larisa.plus.model.enterprise.EnterpriseRepository;
import com.larisa.plus.model.enterprise.SucursalRepository;
import com.larisa.plus.model.user.*;
import com.larisa.plus.model.vehicle.VehicleRepository;
import com.larisa.plus.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping("/articlevehicle")
public class ArticleVehicleController {

    @Autowired
    private UserRepository usrRep;

    @Autowired
    private UserEnterpriseRepository useRep;

    @Autowired
    private CityRepository cityRep;

    @Autowired
    private CantonRepository canRep;

    @Autowired
    private ParroquiaRepository parRep;

    @Autowired
    private ClientRepository cliRep;

    @Autowired
    private EnterpriseRepository entRep;

    @Autowired
    private ItemCatalogRepository icRep;

    @Autowired
    private UserEnterpriseRepository uenRep;

    @Autowired
    private UbicationRepository ubiRep;

    @Autowired
    private ArticleRepository artRep;

    @Autowired
    private ArticleVehicleRepository aveRep;

    @Autowired
    private ArticleSucursalRepository asuRep;

    @Autowired
    private SucursalRepository sucRep;

    @Autowired
    private VehicleRepository vehRep;

    @GetMapping("/edit/{id}")
    public String getList(Model model,  @PathVariable("id") int id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("user",userDetails);

        ArticleVehicle ave = aveRep.findById(id);
        System.out.println("ave: "+ave);
        model.addAttribute("ave", ave);

        model.addAttribute("tipos", icRep.findByCatalog_Id(10));
        return "article_vehicle/edit";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(Model model, HttpServletRequest req) throws ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("user",userDetails);
        int id_article_vehicle = Integer.parseInt(req.getParameter("id_article_vehicle"));

        int id_article = Integer.parseInt(req.getParameter("id_article"));
        int id_vehicle = Integer.parseInt(req.getParameter("id_vehicle"));
        int unidades = Integer.parseInt(req.getParameter("units"));
        String description = req.getParameter("description");
        int id_tipo = Integer.parseInt(req.getParameter("types"));
        int time_change = Integer.parseInt(req.getParameter("time_change"));

        ArticleVehicle ave = null;
        if(id_article_vehicle != -1){
            ave = aveRep.findById(id_article_vehicle);
        }else{
            ave = new ArticleVehicle();
        }
        ave.setArticle(artRep.findById(id_article));
        ave.setVehicle(vehRep.findById(id_vehicle));
        ave.setDescription(description);
        ave.setRegister(new Date());
        ave.setUnits(unidades);
        ave.setStatus(icRep.findById(12));//REGISTRO ACTIVO
        ave.setType_filter(icRep.findById(id_tipo));
        ave.setTime_change(time_change);
        aveRep.save(ave);
        return "redirect:../article/edit/"+ave.getArticle().getCode() ;
    }

}
