package com.shurapili.web.repository;

import com.shurapili.web.models.PostRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRatingRepository  extends JpaRepository<PostRating, Long> {

    List<PostRating> findByUserPostPostId(long post_id);
    List<PostRating> findByUserPostUserId(long user_id);
    PostRating findByUserPostPostIdAndUserPostUserId(long post_id, long user_id);
}
