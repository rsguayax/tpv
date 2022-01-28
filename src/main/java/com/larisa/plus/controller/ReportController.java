package com.larisa.plus.controller;

import com.larisa.plus.model.article.ArticleRepository;
import com.larisa.plus.model.article.ArticleSucursalRepository;
import com.larisa.plus.model.buy.Buy;
import com.larisa.plus.model.buy.BuyRepository;
import com.larisa.plus.model.cashier.CashierRepository;
import com.larisa.plus.model.catalog.CatalogRepository;
import com.larisa.plus.model.catalog.ItemCatalogRepository;
import com.larisa.plus.model.client.ClientRepository;
import com.larisa.plus.model.enterprise.SucursalRepository;
import com.larisa.plus.model.sale.Sale;
import com.larisa.plus.model.sale.SaleDetailRepository;
import com.larisa.plus.model.sale.SaleRepository;
import com.larisa.plus.model.user.UserEnterpriseRepository;
import com.larisa.plus.model.user.UserRepository;
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
@RequestMapping("/report")
public class ReportController {

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
    @Autowired
    private SaleRepository saleRep;

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy");

    /***
     * MÉTODO PARA OBTENER EL REPORTE DIARIO DE CAJA.  SUMAMOS TODO EL EFECTIVO DEL DÍA Y RESTAMOS CON LOS GASTOS DEL DÍA PARA DETERMINAR EL SALDO EFECTIVO DIARIO
     * @param model
     * @param tipo
     * @return
     */
    @GetMapping("/daily_report/{date}")
    public ModelAndView listExpenses(Model model, @PathVariable(value = "date",required = false) String date){ //, @PathVariable("current_date") String fecha_actual
        System.out.println("fecha recibida: "+date);
        ModelAndView mv = new ModelAndView("report/daily_report");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("user", userDetails );
        try {
            //VENTAS
            List<Sale> sales = saleRep.findBySale_date(sdf.parse(date));
            System.out.println("Número de ventas: "+sales.size());
            double efectivo = 0; //itemcatalog = 8
            double transferencia = 0; //itemcatalog = 9
            double tarjeta = 0; //itemcatalog = 10
            double credito = 0; //itemcatalog = 60
            double otro = 0; //itemcatalog = 11

            double total_ventas = 0;
            for (Sale s : sales) {
                total_ventas += s.getTotal();
                if(s.getWay_pay().getId() == 8)
                    efectivo += s.getTotal();
                else if(s.getWay_pay().getId() == 9)
                    transferencia += s.getTotal();
                else if(s.getWay_pay().getId() == 10)
                    tarjeta += s.getTotal();
                else if(s.getWay_pay().getId() == 60)
                    credito += s.getTotal();
                else if(s.getWay_pay().getId() == 11)
                    otro += s.getTotal();
            }
            model.addAttribute("sales", sales);
            model.addAttribute("total_ventas", String.format("%.2f", total_ventas));
            model.addAttribute("efectivo", String.format("%.2f", efectivo));
            model.addAttribute("transferencia", String.format("%.2f", transferencia));
            model.addAttribute("tarjeta", String.format("%.2f", tarjeta));
            model.addAttribute("credito", String.format("%.2f", credito));
            model.addAttribute("otro", String.format("%.2f", otro));

            //GASTOS
            List<Buy> buys = buyRep.findByBuyDate(sdf.parse(date));
            System.out.println("Número de compras: "+buys.size());
            double total_gastos = 0;
            for (Buy s : buys) {
                total_gastos += s.getTotal();
            }
            model.addAttribute("buys", buys);
            model.addAttribute("total_gastos", String.format("%.2f", total_gastos));

            model.addAttribute("date", sdf.parse(date));
            model.addAttribute("total", efectivo - total_gastos);
        }catch(Exception ex){
            System.out.println("error: "+ex.getMessage());
        }

        return mv;
    }
}
