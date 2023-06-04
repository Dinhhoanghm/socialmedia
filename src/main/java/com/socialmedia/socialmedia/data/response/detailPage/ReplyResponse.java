package com.socialmedia.socialmedia.data.response.detailPage;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ReplyResponse {
    private String replyContent;
    private Integer voteCount;
    private String userCommentName;
    private UserResponse userReplyResponse;

}
