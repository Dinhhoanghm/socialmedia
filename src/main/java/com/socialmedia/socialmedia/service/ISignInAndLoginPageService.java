package com.socialmedia.socialmedia.service;

import com.socialmedia.socialmedia.data.request.signinAndLoginPage.UserSignInRequest;
import org.springframework.stereotype.Component;

@Component
public interface ISignInAndLoginPageService {
    public String registerNewUser(UserSignInRequest userRequest);
    public boolean emailExists(String email);
}
