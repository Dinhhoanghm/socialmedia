package com.socialmedia.socialmedia.service;


import com.hm.socialmedia.tables.pojos.*;
import com.socialmedia.socialmedia.data.request.detailPage.CommentRequest;
import com.socialmedia.socialmedia.data.request.detailPage.ReplyRequest;
import com.socialmedia.socialmedia.data.response.detailPage.CommentResponse;
import com.socialmedia.socialmedia.data.response.detailPage.PostResponse;
import com.socialmedia.socialmedia.data.response.detailPage.ReplyResponse;
import com.socialmedia.socialmedia.data.response.detailPage.UserResponse;
import com.socialmedia.socialmedia.mapper.detailPostPage.ReplyMapper;
import com.socialmedia.socialmedia.mapper.detailPostPage.UserMapper;
import com.socialmedia.socialmedia.repository.DetailPostPageRepo;
import com.socialmedia.socialmedia.repository.IDetailPostPageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailPostPageService implements IDetailPostPageService {
    private final IDetailPostPageRepo detailPostPageRepo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReplyMapper replyMapper;

    public DetailPostPageService(DetailPostPageRepo detailPostPageRepo) {
        this.detailPostPageRepo = detailPostPageRepo;
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public List<Comment> getAllNewComment(Integer id) {
        return detailPostPageRepo.getAllNewComments(id);
    }

    public List<Comment> getAllCommonComment(Integer id) {
        return detailPostPageRepo.getAllCommonComments(id);
    }

    public Post getPostById(Integer id) {
        return detailPostPageRepo.getPostById(id);
    }

    public List<Reply> getAllReply(Integer id) {
        return detailPostPageRepo.getAllReply(id);
    }

    public List<String> getRandomRecommendPost(Integer id) {
        List<Post> posts = detailPostPageRepo.getPostByTopic(id);
        List<String> postResponses = new ArrayList<>();
        for (Post posts1 : posts) {
            postResponses.add(posts1.getTitle());
        }
        return postResponses;
    }

    public List<ReplyResponse> getAllReplyResponse(List<Reply> replies) {
        List<ReplyResponse> replyResponses = new ArrayList<>();

        for (Reply reply : replies) {
            User user = detailPostPageRepo.getUser(reply.getUserOwnerId());
            UserResponse userResponse2= userMapper.toDTO(user);
            ReplyResponse replyResponse = replyMapper.toResponse(reply,userResponse2);
            replyResponses.add(replyResponse);
        }
        return replyResponses;
    }

    public List<CommentResponse> getAllCommentResponse(List<Comment> comments) {
        List<CommentResponse> commentResponses = new ArrayList<>();

        for (Comment comments1 : comments) {
            List<Reply> replies = getAllReply(comments1.getId());
            List<ReplyResponse> replyResponses = getAllReplyResponse(replies);
            User user = detailPostPageRepo.getUser(comments1.getUserOwnerId());
            UserResponse userResponse = userMapper.toDTO(user);
            CommentResponse commentResponse = new CommentResponse().setUserCommentResponse(userResponse)
                    .setContent(comments1.getContent())
                    .setReplyCount(comments1.getReplyCount())
                    .setVoteCount(comments1.getVoteCount())
                    .setReplyResponses(replyResponses);
            commentResponses.add(commentResponse);

        }
        return commentResponses;
    }

    @Override
    public PostResponse getPostResponseByDate(Integer id) {
        Post post = getPostById(id);
        List<String> recommendPost = getRandomRecommendPost(post.getTopicRelatedId());
        Topic topic = detailPostPageRepo.getTopicById(post.getTopicRelatedId());
        User user = detailPostPageRepo.getUser(post.getId());
        UserResponse userResponse = new UserResponse().setUserAvatar(user.getAvatar())
                .setUserName(user.getAccount());
        List<Comment> comments = getAllNewComment(post.getId());

        List<CommentResponse> commentResponses = getAllCommentResponse(comments);

        PostResponse postResponse = new PostResponse().setUserResponse(userResponse)
                .setCommentResponses(commentResponses)
                .setContent(post.getContent())
                .setTopic(topic.getContent())
                .setCommentCount(post.getCommentCount())
                .setPostRecommend(recommendPost);

        return postResponse;
    }

    @Override
    public PostResponse getPostResponseByCommon(Integer id) {
        Post post = getPostById(id);
        List<String> recommendPost = getRandomRecommendPost(post.getTopicRelatedId());
        Topic topic = detailPostPageRepo.getTopicById(post.getTopicRelatedId());
        User user = detailPostPageRepo.getUser(post.getId());
        UserResponse userResponse = new UserResponse().setUserAvatar(user.getAvatar())
                .setUserName(user.getAccount());
        List<Comment> comments = getAllCommonComment(post.getId());


        List<CommentResponse> commentResponses = getAllCommentResponse(comments);
        PostResponse postResponse = new PostResponse().setUserResponse(userResponse)
                .setCommentResponses(commentResponses)
                .setPostRecommend(recommendPost)
                .setTopic(topic.getContent())
                .setCommentCount(post.getCommentCount())
                .setContent(post.getContent());

        return postResponse;
    }

    @Override
    public CommentResponse insertComment(CommentRequest commentRequest, Integer id) {
        User user = detailPostPageRepo.getUser(commentRequest.getUserOwnerId());
        Post posts = detailPostPageRepo.getPostById(id);
        Comment comment = new Comment().setContent(commentRequest.getContent())
                .setPostId(id)
                .setPostUserId(posts.getUserOwnerId())
                .setUserOwnerId(commentRequest.getUserOwnerId());
        Comment newComment = detailPostPageRepo.insertComment(comment);
        detailPostPageRepo.updatePostCommentCount(id);
        UserResponse userResponse = new UserResponse().setUserName(user.getAccount())
                .setUserAvatar(user.getAvatar());
        return new CommentResponse().setContent(newComment.getContent())
                .setUserCommentResponse(userResponse).setVoteCount(0).setReplyCount(0);
    }

    @Override
    public ReplyResponse insertReply(ReplyRequest replyRequest, Integer postId, Integer commentId) {
        User user = detailPostPageRepo.getUser(replyRequest.getUserOwnerId());
        Post posts = detailPostPageRepo.getPostById(postId);
        Comment comment = detailPostPageRepo.getCommentById(commentId);
        Reply reply = new Reply().setContent(replyRequest.getContent())
                .setPostId(postId)
                .setCommentId(commentId)
                .setCommentUserId(comment.getUserOwnerId())
                .setUserOwnerId(replyRequest.getUserOwnerId())
                .setPostUserId(posts.getUserOwnerId());
        Reply newReply = detailPostPageRepo.insertReply(reply);
        UserResponse userResponse = new UserResponse().setUserName(user.getAccount())
                .setUserAvatar(user.getAvatar());
        return new ReplyResponse().setReplyContent(newReply.getContent())
                .setVoteCount(0)
                .setUserReplyResponse(userResponse)
                .setUserCommentName(detailPostPageRepo.getUser(comment.getUserOwnerId()).getName());
    }
}

