package com.shurapili.web.service;

import com.shurapili.web.form.UserForm;
import com.shurapili.web.models.Post;
import com.shurapili.web.models.PostComment;
import com.shurapili.web.models.PostRating;
import com.shurapili.web.models.User;
import com.shurapili.web.repository.PostCommentRepository;
import com.shurapili.web.repository.PostRatingRepository;
import com.shurapili.web.repository.PostRepository;
import com.shurapili.web.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostRatingRepository postRatingRepository;
    private final PostCommentRepository postCommentRepository;

    public PostService(PostRepository postRepository, PostRatingRepository postRatingRepository, PostCommentRepository postCommentRepository) {
        this.postRepository = postRepository;
        this.postRatingRepository = postRatingRepository;
        this.postCommentRepository = postCommentRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public Post register(Post postForm, User user) {
        Post post = new Post(postForm.getTitle(), postForm.getText());
        post.setUser(user);
        user.addPost(post);
        postRepository.save(post);
        return post;
    }

    public Post update(Post post, Post postForm) {
        post.setTitle(postForm.getTitle());
        post.setText(postForm.getText());
        postRepository.save(post);
        return post;
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public int getPostRating(Post post) {
        List<PostRating> postRates = postRatingRepository.findByUserPostPostId(post.getId());
        int postRating = 0;
        for (PostRating rate : postRates) {
            postRating += rate.getRate() ? 1 : -1;
        }
        return postRating;
    }

    public Boolean getUserRate(Post post, User user) {
        if (user != null) {
            PostRating userRate = postRatingRepository.findByUserPostPostIdAndUserPostUserId(post.getId(), user.getId());
            return userRate == null ? null : userRate.getRate();
        }
        return null;
    }

    public void Like(Post post, User user) {
        PostRating userRate = postRatingRepository.findByUserPostPostIdAndUserPostUserId(post.getId(), user.getId());
        if (userRate != null) {
            if (userRate.getRate()) {
                postRatingRepository.delete(userRate);
            } else {
                userRate.setRate(true);
                postRatingRepository.save(userRate);
            }
        } else {
            userRate = new PostRating(true, user, post);
            postRatingRepository.save(userRate);
        }
    }

    public void Dislike(Post post, User user) {
        PostRating userRate = postRatingRepository.findByUserPostPostIdAndUserPostUserId(post.getId(), user.getId());
        if (userRate != null) {
            if (!userRate.getRate()) {
                postRatingRepository.delete(userRate);
            } else {
                userRate.setRate(false);
                postRatingRepository.save(userRate);
            }
        } else {
            userRate = new PostRating(false, user, post);
            postRatingRepository.save(userRate);
        }
    }

    public PostComment registerComment(PostComment commentForm, Post post, User user) {
        PostComment comment = new PostComment(commentForm.getText());
        comment.setPost(post);
        comment.setUser(user);
        post.addComment(comment);
        user.addComment(comment);
        postCommentRepository.save(comment);
        return comment;
    }
}
