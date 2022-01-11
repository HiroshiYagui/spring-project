package com.freecode.redditclone.service;

import java.util.Optional;

import com.freecode.redditclone.dto.VoteDto;
import com.freecode.redditclone.exceptions.PostNotFoundException;
import com.freecode.redditclone.exceptions.SpringRedditException;
import com.freecode.redditclone.model.Post;
import com.freecode.redditclone.model.Vote;
import com.freecode.redditclone.repository.PostRepository;
import com.freecode.redditclone.repository.VoteRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

import static com.freecode.redditclone.model.VoteType.UPVOTE;

@Service
@AllArgsConstructor
public class VoteService {
    
    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;


    @Transactional
    public void vote(VoteDto voteDto){
        Post post=postRepository.findById(voteDto.getPostId())
                    .orElseThrow(()->new PostNotFoundException("Post not found with ID-"+voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser =voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if(voteByPostAndUser.isPresent() &&
            voteByPostAndUser.get().getVoteType()
                .equals(voteDto.getVoteType())){
            
                    throw new SpringRedditException("You have already "+ voteDto.getVoteType() + "'d for this post");
            }

        if(UPVOTE.equals(voteDto.getVoteType())){
            post.setVoteCount(post.getVoteCount()+1);
        }else{
            post.setVoteCount(post.getVoteCount()-1);
        }
        voteRepository.save(mapToVote(voteDto,post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post){
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
