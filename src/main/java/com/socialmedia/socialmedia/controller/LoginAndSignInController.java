package com.socialmedia.socialmedia.controller;


import com.socialmedia.socialmedia.config.security.UserLoginInfo;

import com.socialmedia.socialmedia.data.request.signinAndLoginPage.UserLoginRequest;
import com.socialmedia.socialmedia.data.request.signinAndLoginPage.UserSignInRequest;
import com.socialmedia.socialmedia.data.response.signinAndLoginPage.UserLoginResponse;
import com.socialmedia.socialmedia.jwt.JwtUltis;
import com.socialmedia.socialmedia.service.ISignInAndLoginPageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class LoginAndSignInController {
    private final ISignInAndLoginPageService userSignInService;

    private final JwtUltis jwtUltis;
    private final AuthenticationManager authenticationManager;

    public LoginAndSignInController(ISignInAndLoginPageService userSignInService, JwtUltis jwtUltis, AuthenticationManager authenticationManager) {
        this.userSignInService = userSignInService;
        this.jwtUltis = jwtUltis;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/user/registration")
    public String registerUserAccount(@Valid @RequestBody UserSignInRequest userRequest) {


        return userSignInService.registerNewUser(userRequest);
    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginRequest userLoginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUserAccount(), userLoginRequest.getUserPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUltis.generateToken(userLoginRequest.getUserAccount());

        UserLoginInfo userDetails = (UserLoginInfo) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new UserLoginResponse(userDetails.getUsername())
                );
    }

}
