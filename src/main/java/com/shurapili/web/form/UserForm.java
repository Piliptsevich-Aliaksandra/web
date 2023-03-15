package com.shurapili.web.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserForm {
    @NotNull(message = "Login shouldn't be empty")
    @NotEmpty(message = "Login shouldn't be empty")
    @Size(min=4, max=24, message = "Login should contain from 4 to 24 characters")
    @Pattern(regexp = "[a-z]+", message = "Expected lowercase Latin letters")
    private String login;
    @NotNull(message = "Password shouldn't be empty")
    @NotEmpty(message = "Password shouldn't be empty")
    @Size(min=4, max=24, message = "Password should contain from 4 to 24 characters")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
