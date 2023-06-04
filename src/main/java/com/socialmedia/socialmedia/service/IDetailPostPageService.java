package com.socialmedia.socialmedia.service;

import com.hm.socialmedia.tables.pojos.Reply;
import com.socialmedia.socialmedia.data.request.detailPage.CommentRequest;
import com.socialmedia.socialmedia.data.request.detailPage.ReplyRequest;
import com.socialmedia.socialmedia.data.response.detailPage.CommentResponse;
import com.socialmedia.socialmedia.data.response.detailPage.PostResponse;
import com.socialmedia.socialmedia.data.response.detailPage.ReplyResponse;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface IDetailPostPageService {
    public PostResponse getPostResponseByDate (Integer id );
    public PostResponse getPostResponseByCommon (Integer id );
    public ReplyResponse insertReply(ReplyRequest replyRequest, Integer postId, Integer commentId);
    public CommentResponse insertComment(CommentRequest commentRequest, Integer id);
}
