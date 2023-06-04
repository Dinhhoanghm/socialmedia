package com.socialmedia.socialmedia.data.request.signinAndLoginPage;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String userAccount;
    private String userPassword;
}
