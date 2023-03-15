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
public class UserRegisterController extends MainController{
    private final UserFormRegisterValidator userFormRegisterValidator;

    public UserRegisterController(UserFormRegisterValidator userFormRegisterValidator) {
        this.userFormRegisterValidator = userFormRegisterValidator;
    }

    @InitBinder("userForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userFormRegisterValidator);
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    @PostMapping("/register")
    public String signUp(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, Model model, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.register(userForm);
        return "redirect:/enter";
    }

}
