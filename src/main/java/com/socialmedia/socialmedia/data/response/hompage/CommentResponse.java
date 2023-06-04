package com.socialmedia.socialmedia.data.response.hompage;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CommentResponse {
    private String commentDesc;
    private Integer voteCount;
    private Integer replyCount;
    private LocalDateTime createdAt;






}
