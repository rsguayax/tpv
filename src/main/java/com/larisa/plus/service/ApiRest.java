package com.larisa.plus.service;

import com.larisa.plus.model.article.Article;
import com.larisa.plus.model.article.ArticleRepository;
import com.larisa.plus.model.article.ArticleSucursal;
import com.larisa.plus.model.article.ArticleSucursalRepository;
import com.larisa.plus.model.client.Client;
import com.larisa.plus.model.client.ClientRepository;
import com.larisa.plus.model.provider.Provider;
import com.larisa.plus.model.provider.ProviderRepository;
import com.larisa.plus.model.user.*;
import com.larisa.plus.model.vehicle.Vehicle;
import com.larisa.plus.model.vehicle.VehicleRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ApiRest {
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private ClientRepository cliRep;

    @Autowired
    private ProviderRepository proRep;

    @Autowired
    private VehicleRepository vehRep;

    @Autowired
    private ArticleRepository artRep;

    @Autowired
    private ArticleSucursalRepository asRep;

    @Autowired
    private CantonRepository canRep;

    @Autowired
    private ParroquiaRepository parRep;

    @PostMapping("/user")
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
        if(true) {//validamos credenciales para procede con la generación del token
            String token = getJWTToken(username);
            User user = new User();
            user.setUsername(username);
            user.setToken(token);
            return user;
        }
        return null;
    }

    private String getJWTToken(String username) {
        String secretKey = "LarisaPlus++";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ADMIN");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    @RequestMapping("/testapi")
    public String testAPI(){
        return "test API JWT successfully";
    }

    @RequestMapping("/client/search/{param}")
    public List<Client> list_clients(@PathVariable("param") String param) {
        List<Client> clients = cliRep.findByIdentifierOrName(param);
        System.out.println("clients: "+ clients.size());
        return clients;
    }

    @RequestMapping("/client/{id}")
    public Client find_one(@PathVariable("id") int id) {
        Client client = cliRep.findById(id);
        return client;
    }


    @RequestMapping("/article/search/{param}/{sucursal}")
    public List<ArticleSucursal> list_articles(@PathVariable("param") String param, @PathVariable("sucursal") int sucursal) {
        List<ArticleSucursal> articles =  asRep.getArticles(param, param, param, sucursal);
        System.out.println("artículos encontados: "+articles.size());
        return articles;
    }

    //PROVIDER
    @RequestMapping("/provider/search/{param}")
    public List<Provider> list_providers(@PathVariable("param") String param) {
        List<Provider> providers = proRep.findByIdentifierOrName(param);
        System.out.println("providers: "+ providers.size());
        return providers;
    }

    @RequestMapping("/provider/{id}")
    public Provider find_one_provider(@PathVariable("id") int id) {
        Provider provider = proRep.findById(id);
        return provider;
    }

    @RequestMapping("/cantones/{provincia_id}")
    public List<Canton> listar_cantones_por_provincia(@PathVariable("provincia_id") int id) {
        return canRep.findByCity_Id(id);
    }

    @RequestMapping("/parroquias/{canton_id}")
    public List<Parroquia> listar_cantones_por_canton(@PathVariable("canton_id") int id) {
        return parRep.findByCanton_Id(id);
    }

    @RequestMapping("/article/search/{param}")
    public List<Article> list_articles(@PathVariable("param") String param) {
        List<Article> articles =  artRep.getArticles(param, param);
        System.out.println("Artículos encontados: "+articles.size());
        return articles;
    }

    @RequestMapping("/vehicle/search/{param}")
    public List<Vehicle> list_vehicles(@PathVariable("param") String param) {
        List<Vehicle> vehicles =  vehRep.findByBrandOrModel(param, param);
        System.out.println("Vehículos encontados: "+vehicles.size());
        return vehicles;
    }
}
