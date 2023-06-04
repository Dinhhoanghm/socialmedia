package com.socialmedia.socialmedia.controller;


import com.hm.socialmedia.tables.pojos.Post;
import com.hm.socialmedia.tables.pojos.Reply;
import com.hm.socialmedia.tables.records.ReplyRecord;
import com.socialmedia.socialmedia.data.request.detailPage.CommentRequest;
import com.socialmedia.socialmedia.data.request.detailPage.ReplyRequest;
import com.socialmedia.socialmedia.data.response.detailPage.CommentResponse;
import com.socialmedia.socialmedia.data.response.detailPage.PostResponse;
import com.socialmedia.socialmedia.data.response.detailPage.ReplyResponse;
import com.socialmedia.socialmedia.service.IDetailPostPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hompage/detail")
public class DetailPageController {

    @Autowired
    private IDetailPostPageService detailPostPageService;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    @GetMapping("/{id}/newest")
    public ResponseEntity<PostResponse> getPostDetailByDate(@PathVariable Integer id) {
        return new ResponseEntity<PostResponse>(detailPostPageService.getPostResponseByDate(id), HttpStatusCode.valueOf(200));

    }

    @GetMapping("/{id}/common")
    public ResponseEntity<PostResponse> getPostDetailByCommon(@PathVariable Integer id) {
        return new ResponseEntity<PostResponse>(detailPostPageService.getPostResponseByCommon(id), HttpStatusCode.valueOf(200));

    }

    @PostMapping("/{postId}")
    @ResponseBody
    public ResponseEntity<CommentResponse> insertComment(
            @RequestBody CommentRequest commentRequest, @PathVariable("postId") Integer id) {
        return
                new ResponseEntity<CommentResponse>
                        (detailPostPageService.insertComment(commentRequest, id), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/{postId}/reply/{commentId}")
    @ResponseBody
    public ResponseEntity<ReplyResponse> getReply(@RequestBody ReplyRequest replyRequest,
                                                  @PathVariable("postId") Integer postId,
                                                  @PathVariable("commentId") Integer commentId ) {
        return new ResponseEntity<ReplyResponse>(detailPostPageService.insertReply(replyRequest, postId,commentId), HttpStatusCode.valueOf(200));
    }

}
