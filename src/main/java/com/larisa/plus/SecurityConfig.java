/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.larisa.plus;

import com.larisa.plus.model.user.UserRepository;
import com.larisa.plus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author ElreySoft
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userDetailService;

    @Autowired
    private UserRepository usRepo;

    @Autowired
    //@Lazy
    private BCryptPasswordEncoder bcrypt;

    //@Autowired
    //private ProveedorAutenticacionUTPL objUtpl;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryotOasswirdEncoder = new BCryptPasswordEncoder();
        return bCryotOasswirdEncoder;
    }

    /**
     * Método que sirve para probar la autenticación de usuarios igual que
     * configurar en archivo application.properties
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("rsguayax")
//                .password("1234")
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password("admin")
//                .roles("USER", "ADMIN");
        System.out.println("ingreso al método: configure");
        auth.userDetailsService(userDetailService).passwordEncoder(bcrypt);
        System.out.println("clave: "+bcrypt.encode("1234"));

//        auth.authenticationProvider(objUtpl);//PROVEEDOR DE AUTENTICACIÒN UTPL

        System.out.println("RG: " + bcrypt);
        System.out.println("RG " + userDetailService);
    }

    /**
     * Método que sirve para expecificar que cualquier petición debe estar
     * autenticada
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/visor/**").permitAll()
                .antMatchers("/inventory/**").hasAnyAuthority("SUPER_USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable().formLogin()
//                .csrf().and().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/inventory/")
                .usernameParameter("username")
                .passwordParameter("password")
                //                .successHandler(loginSuccessHandler())
                .failureHandler(loginFailureHandler())
                .and()
                .logout()
                .logoutUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/img/**", "/DataTables/**");
    }

    private AuthenticationFailureHandler loginFailureHandler() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        return ((request, response, exception) -> {
            String usuario = request.getParameter("username");
            String clave = request.getParameter("password");
            System.out.println("usuario: " + usuario);
            System.out.println("clave: " + clave);
            //forzamos a realizar autenticación
            /*URL url = new URL(request.getContextPath() + "/login");
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("POST"); // PUT is another valid option
            http.addRequestProperty("username", usuario);
            http.addRequestProperty("password", clave);
            http.setDoOutput(true);*/
            response.sendRedirect(request.getContextPath() + "/home");
        });
    }

//    private AuthenticationSuccessHandler loginSuccessHandler() {
//        return ((request, response, authentication) -> response.sendRedirect(request.getContextPath() + "/"));
//    }
}
