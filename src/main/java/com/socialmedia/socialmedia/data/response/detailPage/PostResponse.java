package com.socialmedia.socialmedia.data.response.detailPage;



import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PostResponse {
    private String content;
    private UserResponse userResponse;
    private List<CommentResponse> commentResponses;
    private List<String> PostRecommend;
    private Integer CommentCount;
    private String Topic;
}
