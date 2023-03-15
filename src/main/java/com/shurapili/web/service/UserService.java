package com.shurapili.web.service;

import com.shurapili.web.form.UserForm;
import com.shurapili.web.models.Post;
import com.shurapili.web.models.User;
import com.shurapili.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(UserForm userForm) {
        User user = new User(userForm.getLogin());
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), userForm.getLogin(), userForm.getPassword());
        return user;
    }

    public User findByLoginAndPassword(UserForm userForm) {
        return userRepository.findByLoginAndPassword(userForm.getLogin(), userForm.getPassword());
    }

    public boolean isLoginVacant(String login) {
        return userRepository.findByLogin(login) == null;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAllByOrderByRegistrationTimeDesc();
    }

    public void writePost(User user, Post post) {
        user.addPost(post);
        userRepository.save(user);
    }

    public void updateAboutText(User user, String aboutText) {
        user.setAboutText(aboutText);
        userRepository.save(user);
    }
}
