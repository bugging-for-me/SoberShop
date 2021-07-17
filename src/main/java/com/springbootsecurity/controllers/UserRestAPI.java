package com.springbootsecurity.controllers;

import com.springbootsecurity.JWTConfiguration.JwtTokenProvider;
import com.springbootsecurity.JWTConfiguration.MyUserDetailsService;
import com.springbootsecurity.models.AuthenticationRequest;
import com.springbootsecurity.models.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
//@RequestMapping("/api")
public class UserRestAPI {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/")
    protected ResponseEntity<?> home() {
        return new ResponseEntity<>("Spring Boot Security !", HttpStatus.OK);
    }

    @GetMapping("/users")
    protected ResponseEntity<?> user() {
        return new ResponseEntity<>("Hello User & Admin !", HttpStatus.OK);
    }

    @GetMapping("/admin")
    protected ResponseEntity<?> admin() {
        return new ResponseEntity<>("Hello Admin !", HttpStatus.OK);
    }

    @PostMapping("/login")
    protected ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            System.out.println(request.toString());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something wrong, error in server !", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtTokenProvider.generateToken(userDetails);
        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
    }
//    try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//            );
//        }
//        catch (BadCredentialsException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Something wrong, error in server !", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(request.getUsername());
//
//        final String jwt = jwtTokenProvider.generateToken(userDetails);
//
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));

}
