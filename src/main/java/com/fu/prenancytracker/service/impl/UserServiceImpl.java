package com.fu.prenancytracker.service.impl;

import com.fu.prenancytracker.model.User;
import com.fu.prenancytracker.repository.UserRepository;
import com.fu.prenancytracker.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
