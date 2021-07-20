package com.blog.demo.controllers;

import com.blog.demo.auth.AuthResponse;
import com.blog.demo.dtos.requests.AuthRequest;
import com.blog.demo.dtos.requests.RegistrationRequest;
import com.blog.demo.models.User;
import com.blog.demo.services.RegistrationService;
import com.blog.demo.services.UserService;
import com.blog.demo.util.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    private User user;

    @Autowired
    private Jwt jwtUtil;

    private RegistrationService registrationService;

    @PostMapping(path = "/sign_up")
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password",e );
        }

        final UserDetails userDetails = userService
                .loadUserByUsername(authRequest.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }


}
