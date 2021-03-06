package com.freecode.redditclone.mapper;

import java.util.Optional;


import com.freecode.redditclone.dto.PostRequest;
import com.freecode.redditclone.dto.PostResponse;
import com.freecode.redditclone.model.Post;
import com.freecode.redditclone.model.Subreddit;
import com.freecode.redditclone.model.Vote;
import com.freecode.redditclone.model.VoteType;
import com.freecode.redditclone.model.User;
import com.freecode.redditclone.repository.CommentRepository;
import com.freecode.redditclone.repository.VoteRepository;
import com.freecode.redditclone.service.AuthService;
import com.github.marlonlom.utilities.timeago.TimeAgo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import static com.freecode.redditclone.model.VoteType.DOWNVOTE;
import static com.freecode.redditclone.model.VoteType.UPVOTE;

@Mapper(componentModel="spring")
public interface PostMapper {

    /*@Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;*/

    

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description" , source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id",source = "postId")
    @Mapping(target = "subredditName" , source="subreddit.name")
    @Mapping(target = "userName",source = "user.username")
    //@Mapping(target = "commentCount",source = "java(commentCount(post))")
    //@Mapping(target = "duration", source = "java(getDuration(post))")
    //@Mapping(target = "upVote",expression = "java(isPostUpVoted(post))")
    //@Mapping(target = "downVote",expression = "java(isPostDownvoted(post))")
    public  PostResponse mapToDto(Post post);

    /*Integer commentCount(Post post){
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post){
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

    boolean isPostUpVoted(Post post){
        return checkVoteType(post,UPVOTE);
    }

    boolean isPostDownvoted(Post post){
        return checkVoteType(post,DOWNVOTE);
    }

    private boolean checkVoteType(Post post,VoteType voteType){
        if(authService.isLoggedIn()){
            Optional<Vote> voteForPostByUser=
                    voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,
                                    authService.getCurrentUser());
            return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType))
                                    .isPresent();
            }

        return false;
    }*/
}
