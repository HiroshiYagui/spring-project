package com.freecode.redditclone.service;

import java.util.List;

import com.freecode.redditclone.dto.CommentDto;
import com.freecode.redditclone.exceptions.PostNotFoundException;
import com.freecode.redditclone.mapper.CommentMapper;
import com.freecode.redditclone.repository.CommentRepository;
import com.freecode.redditclone.repository.PostRepository;
import com.freecode.redditclone.repository.UserRepository;
import com.freecode.redditclone.model.Comment;
import com.freecode.redditclone.model.NotificationEmail;
import com.freecode.redditclone.model.Post;
import com.freecode.redditclone.model.User;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {
    
    private static final String POST_URL="";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentDto commentDto){
        Post post = postRepository.findById(commentDto.getPostId())
                    .orElseThrow(()-> new PostNotFoundException(commentDto.getPostId().toString()));

        Comment comment=commentMapper.map(commentDto, post, authService.getCurrentUser());            
        commentRepository.save(comment);
        
        String message=mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message,post.getUser());
    }

    private void sendCommentNotification(String message, User user){
        mailService.sendMail(new NotificationEmail(user.getUsername()+ "Commented on your post", user.getEmail(),message));
    }

    public List<CommentDto> getAllCommentsForPost(Long postId){
        Post post= postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToCommentDto).collect(toList());
    }

    public List<CommentDto> getAllCommentsForUser(String username){
        User user= userRepository.findByUsername(username)
                    .orElseThrow(()->new UsernameNotFoundException(username));
        
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToCommentDto)
                .collect(toList()); 
    }
}
