package com.springbootsecurity.service;

import com.springbootsecurity.entitys.User;
import com.springbootsecurity.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
}
