package com.larisa.plus.controller;

import com.larisa.plus.model.article.ArticleRepository;
import com.larisa.plus.model.article.ArticleSucursal;
import com.larisa.plus.model.article.ArticleSucursalRepository;
import com.larisa.plus.model.buy.Buy;
import com.larisa.plus.model.buy.BuyRepository;
import com.larisa.plus.model.cashier.CashierRepository;
import com.larisa.plus.model.catalog.CatalogRepository;
import com.larisa.plus.model.catalog.ItemCatalogRepository;
import com.larisa.plus.model.client.Client;
import com.larisa.plus.model.client.ClientRepository;
import com.larisa.plus.model.enterprise.SucursalRepository;
import com.larisa.plus.model.sale.Sale;
import com.larisa.plus.model.sale.SaleDetail;
import com.larisa.plus.model.sale.SaleDetailRepository;
import com.larisa.plus.model.sale.SaleRepository;
import com.larisa.plus.model.user.*;
import com.larisa.plus.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

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
    @Autowired
    private BuyRepository buyRep;


    @GetMapping("/list/{tipo_documento}")
    public ModelAndView listExpenses(Model model, @PathVariable("tipo_documento") int tipo){ //, @PathVariable("current_date") String fecha_actual
        List<Buy> buys =  (List<Buy>)buyRep.findAll();

        double total_gastos = 0;
        for (Buy s : buys){
            total_gastos+=s.getTotal();
        }
        model.addAttribute("buys", buys);
        model.addAttribute("total_gastos", String.format("%.2f", total_gastos));


        ModelAndView mv = new ModelAndView("expense/list");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("user", userDetails );
        return mv;
    }

    @GetMapping("/{buy_id}")
    public ModelAndView getBuy(Model model, @PathVariable("buy_id") int id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("user", userDetails );

        model.addAttribute("type_document", icRep.findByCatalog_Id(3) );/*types of documents*/
        Buy buy = buyRep.getBuyById(id);
        model.addAttribute("buy", buy);
        return new ModelAndView("expense/detail");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String guardar(Model model, HttpServletRequest req) throws ParseException {
        System.out.println("ingreso a guardar");
        try {
            SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            int sucursal = 1;
            int enterprise = 1;
            int id_gasto = Integer.parseInt(req.getParameter("id_gasto"));
            Buy buy = null;
            if (id_gasto == -1) {//guardar nuevo cliente
                //Guardamos un usuario en la tabla global de usuarios
                buy = new Buy();
                buy.setRegister(new Date());

            } else {//editamos gasto existente
                buy = buyRep.getBuyById(id_gasto);
            }
            buy.setBuy_date(sf.parse(req.getParameter("buy_date")));
            buy.setDescription(req.getParameter("motive"));
            buy.setObservation(req.getParameter("observation"));
            buy.setDocument_type(icRep.findById(Integer.parseInt(req.getParameter("document_type"))));
            buy.setDocument_number(req.getParameter("document_number"));
            buy.setTotal(Double.parseDouble(req.getParameter("value")));
            buy.setSucursal(sucRep.findById(sucursal));
            buy.setUser_enterprise( uenRep.findByUser_IdAndEnterprise_Id(userDetails.getId(),  enterprise));
            buyRep.save(buy);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "redirect:list/-1";
    }
}
