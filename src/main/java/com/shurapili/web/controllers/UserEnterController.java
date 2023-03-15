package com.shurapili.web.controllers;

import com.shurapili.web.form.UserForm;
import com.shurapili.web.form.validator.UserFormEnterValidator;
import com.shurapili.web.form.validator.UserFormRegisterValidator;
import com.shurapili.web.models.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserEnterController extends MainController{
    private final UserFormEnterValidator userFormEnterValidator;

    public UserEnterController(UserFormEnterValidator userFormEnterValidator) {
        this.userFormEnterValidator = userFormEnterValidator;
    }

    @InitBinder("userForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userFormEnterValidator);
    }

    @GetMapping("/enter")
    public String login(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "enter";
    }

    @PostMapping("/enter")
    public String enter(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, Model model, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "enter";
        }
        User user = userService.findByLoginAndPassword(userForm);
        httpSession.setAttribute("userId", user.getId());
        return "redirect:/";
    }
}
