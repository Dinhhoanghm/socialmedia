package com.socialmedia.socialmedia.mapper.detailPostPage;

import com.hm.socialmedia.tables.pojos.Comment;
import com.socialmedia.socialmedia.data.response.detailPage.CommentResponse;
import com.socialmedia.socialmedia.data.response.detailPage.ReplyResponse;
import com.socialmedia.socialmedia.data.response.detailPage.UserResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring" ,uses = {UserMapper.class, ReplyMapper.class})
public interface CommentMapper {
    @Mapping(source = "userResponse", target = "userCommentResponse")
    @Mapping(source = "comment.content", target = "content")
    @Mapping(source = "comment.voteCount", target = "voteCount")
    @Mapping(source = "comment.replyCount", target = "replyCount")
    @Mapping(source = "response", target = "replyResponses")
    CommentResponse toDTO (Comment comment, UserResponse userResponse, ReplyResponse response);
}
