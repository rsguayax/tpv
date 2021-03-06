package com.larisa.plus.controller;

import com.larisa.plus.model.article.ArticleRepository;
import com.larisa.plus.model.article.ArticleSucursal;
import com.larisa.plus.model.article.ArticleSucursalRepository;
import com.larisa.plus.model.cashier.CashierRepository;
import com.larisa.plus.model.catalog.CatalogRepository;
import com.larisa.plus.model.catalog.ItemCatalogRepository;
import com.larisa.plus.model.client.ClientRepository;
import com.larisa.plus.model.enterprise.SucursalRepository;
import com.larisa.plus.model.sale.Sale;
import com.larisa.plus.model.sale.SaleDetail;
import com.larisa.plus.model.sale.SaleDetailRepository;
import com.larisa.plus.model.sale.SaleRepository;
import com.larisa.plus.model.user.UserEnterprise;
import com.larisa.plus.model.user.UserEnterpriseRepository;
import com.larisa.plus.model.user.UserRepository;
import com.larisa.plus.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private SaleRepository salRep;
    @Autowired
    private ClientRepository cliRep;
    @Autowired
    private SucursalRepository sucRep;
    @Autowired
    private CashierRepository casRep;
    @Autowired
    private SaleDetailRepository sdeRep;
    @Autowired
    private ArticleSucursalRepository asuRep;

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy");

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
        model.addAttribute("date", sdf.format(new Date()));

        return mv;
    }

    @RequestMapping(value = "/facturar", method = RequestMethod.POST)
    public String facturar(HttpServletRequest req){
        try{
            System.out.println("ingreso a m??todo POST /facturar");
            int client = Integer.parseInt(req.getParameter("client_id"));
            int cashier = 1;//Integer.parseInt(req.getParameter("cashier"));
            int sucursal = 1;
            int estado = 12; //estado activo
            int document_type = Integer.parseInt(req.getParameter("document_type"));
            int way_pay = Integer.parseInt(req.getParameter("way_pay"));
            double total_a_pagar = Double.parseDouble( req.getParameter("input_total_a_pagar") );
            Sale sale = new Sale();
            sale.setClient( cliRep.findById(client) );
            sale.setRegister( new Date() );
            sale.setSale_date( new Date() );
            sale.setCashier( casRep.findById(cashier) );
            sale.setStatus( icRep.findById( estado ) );
            sale.setSucursal( sucRep.findById(sucursal) );
            sale.setDocument_type( icRep.findById( document_type ) );
            sale.setWay_pay( icRep.findById( way_pay ) );
            sale.setTotal( total_a_pagar );
            salRep.save(sale);

            //guardar detalle de factura
            String articles_sucursal[] = req.getParameterValues("id_ps");
            String quantities[] = req.getParameterValues("quantity");
            for(int i = 0; i< articles_sucursal.length; i++){
                System.out.println("ingresando articulo sucursal: "+ articles_sucursal[i]);
                ArticleSucursal asu = asuRep.findById( Integer.parseInt(articles_sucursal[i]) );
                SaleDetail sd = new SaleDetail();
                sd.setRegister(new Date());
                sd.setSale(sale);
                sd.setQuantity( Integer.parseInt(quantities[i]) );
                sd.setArticle_sucursal( asu );
                sd.setSale_price(asu.getSale_price().doubleValue());
                sdeRep.save(sd);
                //ACTUALIZAMOS stock
                asu.setStock( asu.getStock() - sd.getQuantity() );
                asuRep.save( asu );
            }
        }catch(Exception ex){
            System.out.println("ex: "+ex.getMessage());
        }
        return "redirect:sale/";
    }

   // @GetMapping("/list/{current_date}")
    @GetMapping("/list")
    public ModelAndView getSales(Model model){ //, @PathVariable("current_date") String fecha_actual
        List<Sale> sales =  (List<Sale>)salRep.findByStatus_Id(12);// (List<Sale>)salRep.findBySale_date(fecha_actual);

        double total_ventas = 0;

        double total_efectivo = 0;
        double total_credito = 0;
        double total_transferencia = 0;

        for (Sale s : sales){
            total_ventas+=s.getTotal();
            if(s.getWay_pay().getId() == 8){
                total_efectivo+=s.getTotal();
            }else if(s.getWay_pay().getId() == 60){
                total_credito+=s.getTotal();
            }else if(s.getWay_pay().getId() == 9){
                total_transferencia+=s.getTotal();
            }
        }
        model.addAttribute("sales", sales);
        model.addAttribute("total_ventas", String.format("%.2f", total_ventas));

        model.addAttribute("total_efectivo", String.format("%.2f", total_efectivo));
        model.addAttribute("total_credito", String.format("%.2f", total_credito));
        model.addAttribute("total_transferencia", String.format("%.2f", total_transferencia));

        ModelAndView mv = new ModelAndView("sale/list");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("user", userDetails );
        model.addAttribute("date", sdf.format(new Date()));
        return mv;
    }

}
