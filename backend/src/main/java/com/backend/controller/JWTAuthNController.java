package com.backend.controller;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.JWTAuthNRequestDTO;
import com.backend.dto.ResponseObj;
import com.backend.service.JWTAuthNService;
import com.backend.util.Constants;

@RestController
@RequestMapping(value = Constants.API_BASE_URL)
public class JWTAuthNController {

    @Autowired
    private JWTAuthNService jwtAuthService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "login", produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<ResponseObj> getAuthorizationToken(@RequestBody JWTAuthNRequestDTO req) {

        String token = null;

        if (req != null && req.getUsername() != null) {
            Authentication authN = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
            if (authN.isAuthenticated()) {
                token = jwtAuthService.getAuthorizationToken(req.getUsername());
            } else {
                throw new UsernameNotFoundException("user not found");
            }
        }
        ResponseObj res = new ResponseObj(token);
        return ResponseEntity.ok(res);
    }

}
