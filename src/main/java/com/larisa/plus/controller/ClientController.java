package com.larisa.plus.controller;

import com.larisa.plus.model.buy.Buy;
import com.larisa.plus.model.catalog.ItemCatalog;
import com.larisa.plus.model.catalog.ItemCatalogRepository;
import com.larisa.plus.model.client.Client;
import com.larisa.plus.model.client.ClientRepository;
import com.larisa.plus.model.enterprise.Enterprise;
import com.larisa.plus.model.enterprise.EnterpriseRepository;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

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

    @GetMapping("/edit/{id_client}")
    public String getList(Model model,  @PathVariable("id_client") int id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("user",userDetails);
        Client client = null;
        List<Canton> cantones = new ArrayList<>();
        List<Parroquia> parroquias = new ArrayList<>();
        if(id != -1){//cliente existe
            client = cliRep.findById(id);
            cantones = canRep.findByCity_Id(client.getUser_enterprise().getUbication().getParroquia().getCanton().getCity().getId());
            parroquias = parRep.findByCanton_Id(client.getUser_enterprise().getUbication().getParroquia().getCanton().getId());
        }
        model.addAttribute("provincias", cityRep.findAll());
        model.addAttribute("cantones", cantones);
        model.addAttribute("parroquias", parroquias);
        model.addAttribute("client", client);

        int enterprise_id = 1;//LAVADORA EL REY
        UserEnterprise ue = uenRep.findByUser_IdAndEnterprise_Id(userDetails.getId(), enterprise_id);
        model.addAttribute("ue", ue);

        return "client/edit";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(Model model, HttpServletRequest req) throws ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        int id_empresa = Integer.parseInt(req.getParameter("id_empresa"));
        int id_cliente = Integer.parseInt(req.getParameter("id_cliente"));
        UserEnterprise ue;
        Ubication ubication;
        if(id_cliente == -1){//guardar nuevo cliente
            //Guardamos un usuario en la tabla global de usuarios
            User user = new User();
            user.setCellphone(req.getParameter("cellphone"));
            user.setEmail(req.getParameter("email"));
            user.setIdentifier(req.getParameter("identifier"));
            user.setName(req.getParameter("name"));
            user.setLastName(req.getParameter("lastname"));
            user.setRegister(new Date());
            ubication = new Ubication();
            ubication.setRegister(new Date());
            ubication.setParroquia(parRep.findById(Integer.parseInt(req.getParameter("parroquia"))));
            ubication.setDescription(req.getParameter("direction"));
            ubication = ubiRep.save(ubication);
            user.setUbication(ubication);
            usrRep.save(user);
            //Guardamos el usuario dentro de la empresa en contexto.
            //UserEnterprise es la tabla donde se trabajan los usuarios específicos a cada empresa.
            ue = new UserEnterprise();
            ue.setEnterprise(entRep.findById(id_empresa));
            ue.setUser(user);
            ue.setRegister(new Date());
        }else{//editamos cliente existente
            //actualizamos en tabla user_enterprise
            ue = useRep.findByUser_IdAndEnterprise_Id(userDetails.getId(), id_empresa);
            ubication = ue.getUbication();
            ubication.setParroquia(parRep.findById(Integer.parseInt(req.getParameter("parroquia"))));
            ubication.setDescription(req.getParameter("direction"));
            ubication = ubiRep.save(ubication);
        }
        ue.setCellphone(req.getParameter("cellphone"));
        ue.setEmail(req.getParameter("email"));
        ue.setIdentifier(req.getParameter("identifier"));
        ue.setName(req.getParameter("name"));
        ue.setLastName(req.getParameter("lastname"));
        ue.setUbication(ubication);
        useRep.save(ue);
        if(id_cliente == -1){
            Client cliente = new Client();
            cliente.setRegister(new Date());
            cliente.setStatus(icRep.findById(12));//estado de registro ACTIVO
            cliente.setUser_enterprise(ue);
            cliRep.save(cliente);
            id_cliente = cliente.getId();
        }
        return "redirect:edit/"+ id_cliente;
    }

}
