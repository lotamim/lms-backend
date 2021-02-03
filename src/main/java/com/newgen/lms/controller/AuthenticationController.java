package com.newgen.lms.controller;

import com.newgen.lms.common.Constants;
import com.newgen.lms.config.JwtTokenUtil;
import com.newgen.lms.model.ApplicationUser;
import com.newgen.lms.model.JwtRequest;
import com.newgen.lms.service.CustomUserDetailsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping(Constants.LOG_IN)
    public ResponseEntity<String> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
//        Map<String,String> map = new HashMap<>();
        JSONObject map = new JSONObject();
        map.put("username", userDetails.getUsername());
        map.put("password", userDetails.getPassword());
        map.put("token", token);

//        return ResponseEntity.ok(new JwtResponse(token));
        return new ResponseEntity<String>(map.toString(), HttpStatus.OK);
    }

    @PostMapping(Constants.SIGN_UP)
    public Map<String,String> saveUser(@RequestBody ApplicationUser applicationUser) {
        return userDetailsService.save(applicationUser);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @GetMapping(value = "user/list")
    public Map<String, List<ApplicationUser>> userList(){
        return userDetailsService.getUserList();
    }
}
