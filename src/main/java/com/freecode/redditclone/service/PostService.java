package com.freecode.redditclone.service;



import com.freecode.redditclone.dto.PostRequest;
import com.freecode.redditclone.dto.PostResponse;
import com.freecode.redditclone.exceptions.SpringRedditException;
import com.freecode.redditclone.exceptions.SubredditNotFoundException;
import com.freecode.redditclone.exceptions.PostNotFoundException;
import com.freecode.redditclone.mapper.PostMapper;
import com.freecode.redditclone.model.Subreddit;
import com.freecode.redditclone.model.Post;
import com.freecode.redditclone.model.User;
import com.freecode.redditclone.repository.PostRepository;
import com.freecode.redditclone.repository.SubredditRepository;
import com.freecode.redditclone.repository.UserRepository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static java.util.stream.Collectors.toList;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {
    
    private final SubredditRepository subredditRepository;
    private final PostRepository postRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Transactional
    public void create(PostRequest postRequest){
        Subreddit subreddit=subredditRepository.findByName(postRequest.getSubredditName())
                    .orElseThrow(()-> new SpringRedditException("Subreddit not found to post"));

        postRepository.save(postMapper.map(postRequest, subreddit ,authService.getCurrentUser()));
    }

    @Transactional(readOnly=true)
    public PostResponse getPost(Long id){
        Post post= postRepository.findById(id)
                    .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);            
    }

    @Transactional(readOnly=true)
    public List<PostResponse> getAllPosts(){
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
        
    }

    @Transactional(readOnly=true)
    public List<PostResponse> getPostBySubreddit(Long subredditId ){
        Subreddit subreddit=subredditRepository.findById(subredditId)
                            .orElseThrow(()-> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts=postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly=true)
    public List<PostResponse> getPostByUsernarme(String username){
        User user=userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                                .stream()
                                .map(postMapper::mapToDto)
                                .collect(toList());
    }

}
