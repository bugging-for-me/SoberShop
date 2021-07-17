package com.springbootsecurity.service;

import com.springbootsecurity.entitys.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String userName);
}
