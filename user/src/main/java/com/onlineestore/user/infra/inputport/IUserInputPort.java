package com.onlineestore.user.infra.inputport;

import java.util.List;

import com.onlineestore.user.infra.dto.UserDTO;
import com.onlineestore.user.infra.dto.UserOutputDTO;
import com.onlineestore.user.infra.dto.UserUpdateDTO;

public interface IUserInputPort {

    public UserOutputDTO createUser(UserDTO user);

    public UserOutputDTO updateUser(Long id, UserUpdateDTO user);

    public UserOutputDTO getUserById(Long id);

    public List<UserOutputDTO> getAllUsers();

    public String deleteUser(Long id);
}