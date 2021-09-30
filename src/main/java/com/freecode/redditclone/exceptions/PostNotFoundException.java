package com.freecode.redditclone.exceptions;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String Message){
        super(Message);
    }
}
