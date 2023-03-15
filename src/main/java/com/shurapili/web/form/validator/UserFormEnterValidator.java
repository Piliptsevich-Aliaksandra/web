package com.shurapili.web.form.validator;

import com.shurapili.web.form.UserForm;
import com.shurapili.web.models.User;
import com.shurapili.web.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserFormEnterValidator implements Validator {
    private final UserRepository userRepository;

    public UserFormEnterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean supports(Class<?> clazz) {
        return UserForm.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserForm enterForm = (UserForm) target;
            if (userRepository.findByLoginAndPassword(enterForm.getLogin(), enterForm.getPassword()) == null) {
                errors.rejectValue("password", "password.invalid-login-or-password", "Invalid login or password");
            }
        }
    }
}
