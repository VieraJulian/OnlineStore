package com.onlineestore.user.infra.outputport;

import java.util.List;

import com.onlineestore.user.domain.User;

public interface IUserMethods {

    public User save(User user);

    public User getUser(Long id);

    public List<User> getAllUsers();

    public void deleteUser(Long id);
}