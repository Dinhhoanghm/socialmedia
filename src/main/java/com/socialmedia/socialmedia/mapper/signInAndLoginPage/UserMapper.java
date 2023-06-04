package com.socialmedia.socialmedia.mapper.signInAndLoginPage;

import com.hm.socialmedia.tables.pojos.User;
import com.socialmedia.socialmedia.data.request.signinAndLoginPage.UserSignInRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "userName",  target = "name")
    @Mapping(source = "userAccount",  target = "account")
    @Mapping(source = "userEmail",  target = "email")
    @Mapping(source = "userPassword",  target = "password")
    @Mapping(source = "userAvatar",  target = "avatar")
    @Mapping(source = "authority",  target = "authority")
    @Mapping(ignore = true,  target = "id")
    @Mapping(ignore = true , target = "createdAt")
    @Mapping(ignore = true,  target = "deletedAt")
    @Mapping(ignore = true,  target = "updatedAt")
    User toEnity (UserSignInRequest userRequest);
}
