package org.ovamunous.firstspringboot.service;

import org.ovamunous.firstspringboot.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    List<User> getUsers();
    User getUser(int id);
}
