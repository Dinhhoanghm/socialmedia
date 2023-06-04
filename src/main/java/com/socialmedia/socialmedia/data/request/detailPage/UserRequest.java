package com.socialmedia.socialmedia.data.request.detailPage;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class UserRequest {

    private int userId;


    private String userName;



    private String email;

    private String name;


    private String avatar;

    public UserRequest(String username, String email, String name, String avatar) {
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.avatar = avatar;

    }




}
