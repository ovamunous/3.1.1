package org.ovamunous.firstspringboot.dao;

import org.ovamunous.firstspringboot.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    List<User> getUsers();
    User getUserById(int id);
}
