package com.onlineestore.user.infra.outputAdapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlineestore.user.domain.User;
import com.onlineestore.user.infra.outputport.IUserMethods;

@Component
public class UserRepository implements IUserMethods {

    @Autowired
    private IMySQLRepository repo;

    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public User getUser(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

}
