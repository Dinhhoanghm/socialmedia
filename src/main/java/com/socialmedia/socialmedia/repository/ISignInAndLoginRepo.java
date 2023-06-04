package com.socialmedia.socialmedia.repository;

import com.hm.socialmedia.tables.pojos.User;
import org.springframework.stereotype.Component;

@Component
public interface ISignInAndLoginRepo {
    public User findUserByUserName(String userAccount);
    public void insertNewUser(User user);

    public boolean findEmail(String email);
}
