package com.socialmedia.socialmedia.data.request.detailPage;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ReplyRequest {
    private Integer id;
    private Integer userOwnerId;
    private Integer commentUserId;
    private Integer postId;
    private Integer commentId;
    private String content;
}
