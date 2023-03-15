package com.shurapili.web.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
public class Post {

    public Post() {
    }

    public Post(String title, String text) {
        this.title = title;
        this.text = text;
    }

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @OrderBy("creationTime desc")
    private List<PostComment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    private Date creationTime;

    @NotNull(message = "Title shouldn't be empty")
    @NotEmpty(message = "Title shouldn't be empty")
    @Size(max=150, message = "Title should contain up to 150 characters")
    private String title;
    @Lob
    @NotNull(message = "Text shouldn't be empty")
    @NotEmpty(message = "Text shouldn't be empty")
    @Size(max=10000, message = "Text should contain up to 10000 characters")
    private String text;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public String getFormattedCreationTime() {
        return new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).format(creationTime);
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
