package com.freecode.redditclone.mapper;

import com.freecode.redditclone.dto.CommentDto;
import com.freecode.redditclone.model.Comment;
import com.freecode.redditclone.model.Post;
import com.freecode.redditclone.model.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "text", source="commentDto.text")
    @Mapping(target = "createdDate",expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source="post")
    Comment map(CommentDto commentDto, Post post, User user);

    @Mapping(target = "postId",expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName",expression = "java(comment.getUser().getUsername())")
    CommentDto mapToCommentDto(Comment comment); 



}
