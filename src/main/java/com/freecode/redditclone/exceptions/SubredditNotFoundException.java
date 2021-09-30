package com.freecode.redditclone.exceptions;

public class SubredditNotFoundException extends RuntimeException {
    public SubredditNotFoundException(String Message){
        super(Message);
    }
}
