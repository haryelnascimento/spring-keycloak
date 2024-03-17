package com.example.springkeycloack.domain.service;

import com.example.springkeycloack.domain.model.User;
import com.example.springkeycloack.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(Integer id, User user) {
        User userSave = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        copyProperties(user, userSave, "id");

        return userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
