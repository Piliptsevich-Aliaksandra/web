package com.shurapili.web.controllers;


import com.shurapili.web.form.UserForm;
import com.shurapili.web.models.Post;
import com.shurapili.web.models.PostComment;
import com.shurapili.web.models.PostRating;
import com.shurapili.web.models.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class BlogController extends MainController {

    @GetMapping("/blog")
    public String blogMain(Model model) {
        List<Post> allPosts = postService.findAll();
        model.addAttribute("allPosts", allPosts);
        return "blogMain";
    }

    @GetMapping("/blog/add/{id}")
    public String blogAdd(Model model) {
        model.addAttribute("postForm", new Post());
        return "blogAdd";
    }

    @PostMapping("/blog/add/{id}")
    public String blogAdd(@PathVariable(value = "id") Long id, @Valid @ModelAttribute("postForm") Post postForm,
                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "blogAdd";
        }

        User user = userService.findById(id);
        postService.register(postForm, user);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") Long id, Model model, HttpSession httpSession) {
        Post post = postService.findById(id);
        User user = getUser(httpSession);
        if (post == null) {
            return "redirect:/blog";
        }
        model.addAttribute("post", post);
        model.addAttribute("postRating", postService.getPostRating(post));
        model.addAttribute("userRate", postService.getUserRate(post, user));
        model.addAttribute("commentForm", new PostComment());
        return "blogDetails";
    }

    @PostMapping("/blog/{id}")
    public String commentAdd(@PathVariable(value = "id") Long id, @Valid @ModelAttribute("commentForm") PostComment commentForm, BindingResult bindingResult, Model model, HttpSession httpSession) {
        Post post = postRepository.findById(id).orElse(null);
        User user = getUser(httpSession);
        if (post == null || user == null) {
            return "redirect:/blog";
        } else {
            if (bindingResult.hasErrors()) {
                return "redirect:/blog/" + post.getId();
            }
            postService.registerComment(commentForm, post, user);
            return "redirect:/blog/" + post.getId();
        }
    }


    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") Long id, Model model) {
        Post post = postService.findById(id);
        if (post == null) {
            return "redirect:/blog";
        } else {
            Post postForm = new Post();
            postForm.setTitle(post.getTitle());
            postForm.setText(post.getText());
            model.addAttribute("postForm", postForm);
            return "blogEdit";
        }
    }

    @PostMapping("/blog/{id}/edit")
    public String blogUpdate(@PathVariable(value = "id") Long id,  @Valid @ModelAttribute("postForm") Post postForm,
                             BindingResult bindingResult, Model model) {
        Post post = postService.findById(id);
        if (post == null) {
            return "redirect:/blog";
        } else {
            if (bindingResult.hasErrors()) {
                model.addAttribute("post", postForm);
                return "blogEdit";
            }
            postService.update(post, postForm);
            return "redirect:/blog/" + post.getId();
        }
    }

    @GetMapping("/blog/{id}/delete")
    public String blogDelete(@PathVariable(value = "id") Long id, Model model) {
        Post post = postService.findById(id);
        if (post == null) {
            return "redirect:/blog";
        } else {
            postService.delete(post);
            return "redirect:/blog";
        }
    }

    @GetMapping("/blog/{id}/like")
    public String blogLike(@PathVariable(value = "id") Long id, Model model, HttpSession httpSession) {
        Post post = postService.findById(id);
        User user = getUser(httpSession);
        if (post == null) {
            return "redirect:/blog";
        } else if (user == null) {
            return "redirect:/blog/" + post.getId();
        } else {
            postService.Like(post, user);
            return "redirect:/blog/" + post.getId();
        }
    }

    @GetMapping("/blog/{id}/dislike")
    public String blogDisike(@PathVariable(value = "id") Long id, Model model, HttpSession httpSession) {
        Post post = postService.findById(id);
        User user = getUser(httpSession);
        if (post == null) {
            return "redirect:/blog";
        } else if (user == null) {
            return "redirect:/blog/" + post.getId();
        } else {
            postService.Dislike(post, user);
            return "redirect:/blog/" + post.getId();
        }
    }
}

