package com.freecode.redditclone.mapper;

import java.util.List;

import com.freecode.redditclone.dto.SubredditDto;
import com.freecode.redditclone.model.Post;
import com.freecode.redditclone.model.Subreddit;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubredditMapper {


    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto mapSubredditDto(Subreddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts){ return numberOfPosts.size();}

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtoToSubreddit(SubredditDto subredditDto);
}
