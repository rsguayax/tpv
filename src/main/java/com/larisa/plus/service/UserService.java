/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.larisa.plus.service;

import com.larisa.plus.model.user.User;
import com.larisa.plus.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author labocienciadedatos
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository usRep;

//    @Autowired
//    private BCryptPasswordEncoder encoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //ir a consultar el usuario en ldap
        System.out.println("ingreso al método loadUserByUsername");
        CustomUserDetails userDet = null;

//        if (username.contains("@")) {
//            username = username.substring(0, username.indexOf("@"));
//        }
//        Autenticacion t = new Autenticacion();
        if (username.contains("@")) {
            username = username.substring(0, username.indexOf("@"));
            System.out.println("limpieza de usuario: " + username);
        }

        User us = usRep.findByUsername(username);
        System.out.println("Usuario encontrado: "+ us.getUsername());

    /**GUARDAR USUARIO.....*/

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ADMIN"));//roles a poblar por db

//        UserDetails userDet = new User(us.getNombres(), us.getClave(), roles);
        userDet = new CustomUserDetails(us.getName(), us.getPassword(), roles);
        userDet.setId(us.getId());
        userDet.setUsername(us.getUsername());
        userDet.setName(us.getName());

        String foto = "img/user-profile.png";
        userDet.setPhoto(foto);

        if (us != null) {
            us.setLastaccess(new Date());
            usRep.save(us);
        }
        return userDet;
    }

}
