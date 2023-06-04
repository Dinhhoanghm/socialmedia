package com.socialmedia.socialmedia.mapper.detailPostPage;

import com.hm.socialmedia.tables.pojos.Reply;
import com.socialmedia.socialmedia.data.response.detailPage.ReplyResponse;
import com.socialmedia.socialmedia.data.response.detailPage.UserResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring" ,uses = UserMapper.class)
public interface ReplyMapper {
    @Mapping(target = "replyContent",source = "reply.content")
    @Mapping(target = "voteCount",source = "reply.voteCount")
    @Mapping(target = "userReplyResponse",source = "userResponse")
    ReplyResponse toResponse (Reply reply, UserResponse userResponse);


}
