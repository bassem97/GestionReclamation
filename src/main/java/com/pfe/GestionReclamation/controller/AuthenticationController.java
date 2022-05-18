package com.pfe.GestionReclamation.controller;


import com.pfe.GestionReclamation.Config.Security.SecurityConfig;
import com.pfe.GestionReclamation.Config.Security.TokenProvider;
import com.pfe.GestionReclamation.service.Email.EmailService;
import com.pfe.GestionReclamation.service.User.UserService;
import com.pfe.GestionReclamation.util.JwtRespone;
import com.pfe.GestionReclamation.util.LoginModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Import(SecurityConfig.class)
@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("auth/")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;



//    @PostMapping("login/{isRemembered}")
//    public ResponseEntity<?> authenticate(@RequestBody LoginModel loginModel, @PathVariable("isRemembered") boolean isRemembered) {
//        return null;
//    }

    @PostMapping("login")
    public ResponseEntity<?> authenticate(@RequestBody LoginModel loginModel){
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginModel.getEmail(),
                        loginModel.getPassword()
                )
        );


        UserDetails userDetails = userDetailsService.loadUserByUsername(loginModel.getEmail());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token;
//        if (!isRemembered)
            token = tokenProvider.generateToken(userDetails,1);
//        else token = tokenProvider.generateToken(userDetails,9999);
        return ResponseEntity.ok(new JwtRespone(token,userService.findByEmail(loginModel.getEmail())));
    }
}
