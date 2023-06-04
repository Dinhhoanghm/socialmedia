package com.socialmedia.socialmedia.data.response.detailPage;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CommentResponse {
    private UserResponse userCommentResponse;
    private String content;
    private Integer voteCount;
    private Integer replyCount;
    private List<ReplyResponse> replyResponses;
}
