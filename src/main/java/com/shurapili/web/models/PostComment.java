package com.shurapili.web.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
public class PostComment {

    public PostComment() {
    }

    public PostComment(String text) {
        this.text = text;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    private Date creationTime;
    @Lob
    @NotNull(message = "Text shouldn't be empty")
    @NotEmpty(message = "Text shouldn't be empty")
    @Size(max=1000, message = "Text should contain up to 1000 characters")
    private String text;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFormattedCreationTime() {
        return new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).format(creationTime);
    }
}
