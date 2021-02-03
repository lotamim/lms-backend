package com.newgen.lms.service;
/*
  Note : PLz don't remove this code with any permission.If you need some change plz concern with
  your Technical Lead or Line manager ;
  Author : Mehedi Hasan Tamim
 */

import com.newgen.lms.model.ApplicationUser;
import com.newgen.lms.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final String SUCCESS_MESSAGE = "success";
    private static final String USER_LIST = "userList";
    private static final String USER_EXIT = "userExit";

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = applicationUserRepository.findByUsername(username);
        List<GrantedAuthority> authorites = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.getUsername(), user.getPassword(),user.isEnabled(),user.isAccountNonLocked(),
                user.isAccountNonLocked(),user.isCredentialsNonExpired(),authorites);
    }

    public Map<String,String> save(ApplicationUser user) {
        Map<String,String> msgMap = new HashMap<>();
        try {
            ApplicationUser applicationUser = new ApplicationUser();
            int dupUsernameCheck = applicationUserRepository.duplicateCheck(user.getUsername());
            if (dupUsernameCheck >= 1) {
                 msgMap.put(USER_EXIT,user.getUsername() + " " + "already exits");
            } else {
                applicationUser.setUsername(user.getUsername());
                applicationUser.setPassword(bcryptEncoder.encode(user.getPassword()));
                applicationUser.setEmail(user.getEmail());
                applicationUserRepository.save(applicationUser);
                msgMap.put(SUCCESS_MESSAGE,"Data Save Successfully");
                return  msgMap;
            }
        } catch (Exception ex) {
            msgMap.put("error",ex.getMessage());
            return msgMap;
        }
        return  msgMap;
    }

    public Map<String, List<ApplicationUser>> getUserList() {
        Map<String, List<ApplicationUser>> dMap = new HashMap<>();
        try {
            List<ApplicationUser> userList = applicationUserRepository.findAll();
            dMap.put(USER_LIST, userList);
            return dMap;
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
