package com.freecode.redditclone.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;
    @NotEmpty
    private String text;
    @ManyToOne(fetch=LAZY)
    @JoinColumn(name="postId",referencedColumnName="postId")
    private Post post;
    private Instant createdDate;
    @ManyToOne(fetch=LAZY)
    @JoinColumn(name="userId",referencedColumnName="userId")
    private User user;
}
