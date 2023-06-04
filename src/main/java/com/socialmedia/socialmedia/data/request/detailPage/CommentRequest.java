package com.socialmedia.socialmedia.data.request.detailPage;


import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class CommentRequest {


    private String Content;

    private int userOwnerId;

    private int postId;



//    private UserRequest userRequest;
}
