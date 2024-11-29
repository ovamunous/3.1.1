package org.ovamunous.firstspringboot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.ovamunous.firstspringboot.model.User;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(@Valid User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(@Valid User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(@Valid User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return (List<User>) em.createQuery("FROM User").getResultList();
    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }
}
