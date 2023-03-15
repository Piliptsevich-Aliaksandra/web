package com.shurapili.web.form.validator;

import com.shurapili.web.form.UserForm;
import com.shurapili.web.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserFormRegisterValidator implements Validator {
    private final UserRepository userRepository;

    public UserFormRegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean supports(Class<?> clazz) {
        return UserForm.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserForm registerForm = (UserForm) target;
            if (userRepository.findByLogin(registerForm.getLogin()) != null) {
                errors.rejectValue("login", "login.is-in-use", "Login is in use already");
            }
        }
    }
}
