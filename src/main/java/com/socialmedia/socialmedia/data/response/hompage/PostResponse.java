/*
 * This file is generated by jOOQ.
 */
package com.socialmedia.socialmedia.data.response.hompage;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostResponse {

    private String PostDesc;
    private String PostUserAvatar;
    private String PostUserName;
    private UserResponse userResponse;
    private CommentResponse commentResponse;
}