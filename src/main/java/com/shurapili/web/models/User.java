package com.shurapili.web.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
public class User {

    public User() {

    }

    public User(String login) {
        this.login = login;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OrderBy("creationTime desc")
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OrderBy("creationTime desc")
    private List<PostComment> comments;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    private Date registrationTime;
    @NotNull(message = "Login shouldn't be empty")
    @NotEmpty(message = "Login shouldn't be empty")
    @Size(min=4, max=24, message = "Login should contain from 4 to 24 characters")
    @Pattern(regexp = "[a-z]+", message = "Expected lowercase Latin letters")
    private String login;
    @Lob
    private String aboutText;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        getPosts().add(post);
    }

    public String getFormattedRegistrationTime() {
        return new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).format(registrationTime);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAboutText() {
        return aboutText;
    }

    public void setAboutText(String aboutText) {
        this.aboutText = aboutText;
    }

    public List<PostComment> getComments() {
        return comments;
    }

    public void addComment(PostComment comment) {
        this.comments.add(comment);
    }

    public void setComments(List<PostComment> comments) {
        this.comments = comments;
    }

}
