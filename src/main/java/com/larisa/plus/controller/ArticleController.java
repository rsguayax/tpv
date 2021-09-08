package com.larisa.plus.controller;

import com.larisa.plus.model.article.Article;
import com.larisa.plus.model.article.ArticleRepository;
import com.larisa.plus.model.article.ArticleSucursal;
import com.larisa.plus.model.article.ArticleSucursalRepository;
import com.larisa.plus.model.article_vehicle.ArticleVehicle;
import com.larisa.plus.model.article_vehicle.ArticleVehicleRepository;
import com.larisa.plus.model.catalog.ItemCatalogRepository;
import com.larisa.plus.model.client.Client;
import com.larisa.plus.model.client.ClientRepository;
import com.larisa.plus.model.enterprise.EnterpriseRepository;
import com.larisa.plus.model.enterprise.SucursalRepository;
import com.larisa.plus.model.user.*;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

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
    private ArticleSucursalRepository asuRep;

    @Autowired
    private SucursalRepository sucRep;

    @Autowired
    private ArticleVehicleRepository aveRep;

    @GetMapping("/edit/{codigo}")
    public String getList(Model model,  @PathVariable("codigo") String codigo){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("user",userDetails);
        int sucursal_id = 1;//LAVADORA EL REY
        ArticleSucursal asu = asuRep.findByArticle_CodeAndSucursal_Id(codigo, sucursal_id);
        model.addAttribute("asu", asu);
        model.addAttribute("marcas", icRep.findByCatalog_Id(6));
        model.addAttribute("medidas", icRep.findByCatalog_Id(7));
        model.addAttribute("categorias", icRep.findByCatalog_Id(8));
        //lista de vehículos vinculados a un artículo
        if(asu == null)
            model.addAttribute("avehiculos", null);
        else
            model.addAttribute("avehiculos", aveRep.findByArticle_Id(asu.getArticle().getId()));

        return "article/edit";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(Model model, HttpServletRequest req) throws ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("user",userDetails);
        int id_sucursal = Integer.parseInt(req.getParameter("id_sucursal"));
        String codigo = req.getParameter("code");
        ArticleSucursal as = asuRep.findByArticle_CodeAndSucursal_Id(codigo, id_sucursal);
        if(as == null){//primero verificamos si existe artículo
            Article article = artRep.findByCode(codigo);
            if(article == null){//creamos nuevo artículo en tabla global de artículos
                article = new Article();
                article.setCode(codigo);
                article.setCategory(icRep.findById(Integer.parseInt(req.getParameter("category"))));
                article.setDescription(req.getParameter("description"));
                article.setMark(icRep.findById(Integer.parseInt(req.getParameter("mark"))));
                article.setMeasure(icRep.findById(Integer.parseInt(req.getParameter("measure"))));
                article.setName(req.getParameter("name"));
                article.setRegister(new Date());
                article.setStatus(icRep.findById(12)); //REGISTRO ACTIVO = 12
                article = artRep.save(article);
            }
            //creamos artículo para la sucursal en contexto
            as = new ArticleSucursal();
            as.setArticle(article);
            as.setRegister(new Date());
            as.setSucursal(sucRep.findById(id_sucursal));
        }
        as.setCode(codigo);
        as.setCategory(icRep.findById(Integer.parseInt(req.getParameter("category"))));
        as.setDescription(req.getParameter("description"));
        as.setMark(icRep.findById(Integer.parseInt(req.getParameter("mark"))));
        as.setMeasure(icRep.findById(Integer.parseInt(req.getParameter("measure"))));
        as.setName(req.getParameter("name"));
        as.setStock(Integer.parseInt(req.getParameter("stock")));
        as.setStatus(icRep.findById(12)); //REGISTRO ACTIVO = 12
        as.setSale_price(new BigDecimal(Double.parseDouble(req.getParameter("saleprice"))));
        as.setBuy_price(new BigDecimal(Double.parseDouble(req.getParameter("buyprice"))));
        if (req.getParameterMap().containsKey("status")) {
            as.setStatus(icRep.findById(12));//activo
        }else{
            as.setStatus(icRep.findById(13));//inactivo
        }
        asuRep.save(as);

        return "redirect:edit/"+ as.getCode();
    }

}
