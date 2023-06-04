package com.socialmedia.socialmedia.mapper.detailPostPage;

import com.hm.socialmedia.tables.pojos.User;
import com.socialmedia.socialmedia.data.response.detailPage.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")

public interface UserMapper {
    @Mapping(target = "userName", source = "name")
    @Mapping(target = "userAvatar", source = "avatar")
    UserResponse toDTO (User user);

}
