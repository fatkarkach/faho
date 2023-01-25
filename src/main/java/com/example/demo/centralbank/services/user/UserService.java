package com.example.demo.centralbank.services.user;

import com.example.demo.centralbank.models.User;

import java.util.List;

public interface UserService {

    User addNewUser(User user);

    User loadUserByEmail(String email);

    List<User> listUsers();

    User findOne(String email);

}
