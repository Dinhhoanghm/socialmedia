package com.socialmedia.socialmedia.service;

import com.hm.socialmedia.tables.pojos.User;
import com.socialmedia.socialmedia.data.request.signinAndLoginPage.UserSignInRequest;
import com.socialmedia.socialmedia.mapper.signInAndLoginPage.UserMapper;
import com.socialmedia.socialmedia.repository.ISignInAndLoginRepo;
import com.socialmedia.socialmedia.repository.SignInAndLoginRepoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignInAndLoginPageAService implements ISignInAndLoginPageService {


    private final ISignInAndLoginRepo userSignInRepo;

    private final UserMapper userMapper;
    public SignInAndLoginPageAService(SignInAndLoginRepoImpl userSignInRepo, UserMapper userMapper) {
        this.userSignInRepo = userSignInRepo;
        this.userMapper = userMapper;
    }

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public String registerNewUser(UserSignInRequest userRequest) {
        if (emailExists(userRequest.getUserEmail())) {
            return "Email has already been registered";
        }
        User user = userMapper.toEnity(userRequest);
        userSignInRepo.insertNewUser(user);

        return "Account is registered successfully";
    }

    @Override
    public boolean emailExists(String email) {
        return userSignInRepo.findEmail(email);
    }
}
