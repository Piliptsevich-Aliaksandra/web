package com.shurapili.web.models;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;

@Entity
public class PostRating {

    public PostRating() {}

    public PostRating(boolean rate, User user, Post post) {
        this.rate = rate;
        this.userPost = new UserPost(user, post);
    }

    @NotNull
    boolean rate;

    @EmbeddedId
    private UserPost userPost;


    public boolean getRate() {
        return rate;
    }

    public void setRate(boolean rate) {
        this.rate = rate;
    }

    public UserPost getUserPost() {
        return userPost;
    }

    public void setUserPost(UserPost userPost) {
        this.userPost = userPost;
    }
}

@Embeddable
class UserPost implements Serializable {
    public UserPost(){}

    public UserPost(User user, Post post){
        this.user = user;
        this.post = post;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    public Post post;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
