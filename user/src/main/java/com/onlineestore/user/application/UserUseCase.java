package com.onlineestore.user.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineestore.user.domain.ERole;
import com.onlineestore.user.domain.User;
import com.onlineestore.user.infra.dto.UserDTO;
import com.onlineestore.user.infra.dto.UserOutputDTO;
import com.onlineestore.user.infra.dto.UserUpdateDTO;
import com.onlineestore.user.infra.inputport.IUserInputPort;
import com.onlineestore.user.infra.outputport.IUserMethods;
import com.onlineestore.user.domain.service.encryptor;

@Service
public class UserUseCase implements IUserInputPort {

    @Autowired
    private IUserMethods userMethods;

    @Override
    public UserOutputDTO createUser(UserDTO user) {

        String role = user.getRole().toUpperCase();

        User userInfo = User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(encryptor.encrypt(user.getPassword()))
                .address(user.getAddress())
                .role(ERole.valueOf(role))
                .build();

        User userNew = userMethods.save(userInfo);

        return UserOutputDTO.builder()
                .id(userNew.getId())
                .username(userNew.getUsername())
                .email(userNew.getEmail())
                .address(userNew.getAddress())
                .role(userNew.getRole().toString())
                .build();
    }

    @Override
    public UserOutputDTO updateUser(Long id, UserUpdateDTO user) {

        User userFound = userMethods.getUser(id);

        if (userFound == null) {
            return null;
        }

        if (user.getPassword() != null) {
            String password = encryptor.encrypt(user.getPassword());

            if (password.equals(userFound.getPassword())) {
                userFound.setPassword(encryptor.encrypt(user.getNewPassword()));
            }
        }

        userFound.setUsername(user.getUsername());
        userFound.setAddress(user.getAddress());

        User userUpdated = userMethods.save(userFound);

        return UserOutputDTO.builder()
                .id(userUpdated.getId())
                .username(userUpdated.getUsername())
                .email(userFound.getEmail())
                .address(userFound.getAddress())
                .role(userFound.getRole().toString())
                .build();

    }

    @Override
    public UserOutputDTO getUserById(Long id) {
        User userFound = userMethods.getUser(id);

        if (userFound == null) {
            return null;
        }

        return UserOutputDTO.builder()
                .id(userFound.getId())
                .username(userFound.getUsername())
                .email(userFound.getEmail())
                .address(userFound.getAddress())
                .role(userFound.getRole().toString())
                .build();
    }

    @Override
    public List<UserOutputDTO> getAllUsers() {
        List<User> usersDB = userMethods.getAllUsers();

        List<UserOutputDTO> users = new ArrayList<>();

        for (User user : usersDB) {
            UserOutputDTO UserFor = UserOutputDTO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .address(user.getAddress())
                    .role(user.getRole().toString())
                    .build();

            users.add(UserFor);
        }

        return users;

    }

    @Override
    public String deleteUser(Long id) {
        userMethods.deleteUser(id);

        return "User deleted success!";
    }

}
