package com.newgen.hrm.service;
/*
  Note : PLz don't remove this code with any permission.If you need some change plz concern with
  your Technical Lead or Line manager ;
  Author : Mehedi Hasan Tamim
 */

import com.newgen.hrm.model.ApplicationUser;
import com.newgen.hrm.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private static final String USER_NAME_EXIT = "Username already exits !";
    private static final String SUCCESS_MESSAGE = "Data Save Successfully !";
    private static final String USER_LIST = "userList";

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
        return new User(user.getUsername(), user.getPassword(), authorites);
    }

    public ResponseEntity<?> save(ApplicationUser user) {
        try {
            ApplicationUser newUser = new ApplicationUser();
            int dupUsernameCheck = applicationUserRepository.duplicateCheck(user.getUsername());
            if (dupUsernameCheck >= 1) {
                return new ResponseEntity<>(USER_NAME_EXIT, HttpStatus.CONFLICT);
            } else {
                newUser.setUsername(user.getUsername());
                newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
                newUser.setEmail(user.getEmail());
                applicationUserRepository.save(newUser);
                return new ResponseEntity<>(SUCCESS_MESSAGE, HttpStatus.OK);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
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
