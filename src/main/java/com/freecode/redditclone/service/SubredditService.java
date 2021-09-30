package com.freecode.redditclone.service;

import java.util.List;


import com.freecode.redditclone.dto.SubredditDto;
import com.freecode.redditclone.exceptions.SpringRedditException;
import com.freecode.redditclone.mapper.SubredditMapper;
import com.freecode.redditclone.model.Subreddit;
import com.freecode.redditclone.repository.SubredditRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {
    
    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto){
        Subreddit save=subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                            .stream()
                            .map(subredditMapper::mapSubredditDto)
                            .collect(toList());
    }

    @Transactional
    public SubredditDto getSubreddit(Long id){
        Subreddit subreddit=subredditRepository.findById(id)
            .orElseThrow(()-> new SpringRedditException("Subreddit not found"));

        return subredditMapper.mapSubredditDto(subreddit);    
    }
}
