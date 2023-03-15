package com.shurapili.web.controllers;

import com.shurapili.web.form.UserForm;
import com.shurapili.web.form.validator.UserFormEnterValidator;
import com.shurapili.web.form.validator.UserFormRegisterValidator;
import com.shurapili.web.models.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController extends MainController {

    @GetMapping("/logout")
    public String logout(Model model, HttpSession httpSession) {
        httpSession.removeAttribute("userId");
        return "redirect:/";
    }

    @GetMapping("/user/{id}")
    public String userDetails(@PathVariable(value = "id") Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/home";
        } else {
            model.addAttribute("userDetails", user);
            return "userDetails";
        }
    }

    @GetMapping("/user/{id}/about/edit")
    public String userAboutEdit(@PathVariable(value = "id") Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/home";
        } else {
            return "userAboutEdit";
        }
    }

    @PostMapping("/user/{id}/about/edit")
    public String userAboutUpdate(@PathVariable(value = "id") Long id, @RequestParam String aboutText, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/home";
        } else {
            userService.updateAboutText(user, aboutText);
            return "redirect:/user/" + user.getId();
        }
    }

    @GetMapping("/user/{id}/about/delete")
    public String userAboutDelete(@PathVariable(value = "id") Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/home";
        } else {
            userService.updateAboutText(user, null);
            return "redirect:/user/" + user.getId();
        }
    }

    @GetMapping("/user/{id}/posts")
    public String userPosts(@PathVariable(value = "id") Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/home";
        } else {
            Iterable<Post> allPosts = user.getPosts();
            model.addAttribute("allPosts", allPosts);
            return "blogMain";
        }
    }

    @GetMapping("/user/{id}/comments")
    public String userComments(@PathVariable(value = "id") Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/home";
        } else {
            Iterable<PostComment> allComments = user.getComments();
            model.addAttribute("allComments", allComments);
            return "userComments";
        }
    }

}
