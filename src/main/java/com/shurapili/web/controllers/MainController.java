package com.shurapili.web.controllers;

import com.shurapili.web.models.*;
import com.shurapili.web.repository.*;
import com.shurapili.web.service.PostService;
import com.shurapili.web.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;



public class MainController {

    @Autowired
    protected UserService userService;
    @Autowired
    protected PostService postService;

    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected PostRepository postRepository;
    @Autowired
    protected PostCommentRepository postCommentRepository;
    @Autowired
    protected PostRatingRepository postRatingRepository;

    @ModelAttribute("user")
    public User getUser(HttpSession httpSession) {
        Object id = httpSession.getAttribute("userId");
        if (id == null) {
            return null;
        } else {
            return userRepository.findById((long) id).orElse(null);
        }
    }
}
