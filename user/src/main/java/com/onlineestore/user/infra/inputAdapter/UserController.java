package com.onlineestore.user.infra.inputAdapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineestore.user.infra.dto.UserDTO;
import com.onlineestore.user.infra.dto.UserOutputDTO;
import com.onlineestore.user.infra.dto.UserUpdateDTO;
import com.onlineestore.user.infra.inputport.IUserInputPort;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserInputPort userInputPort;

    @PostMapping("/create")
    public ResponseEntity<UserOutputDTO> createUser(@RequestBody UserDTO user) {
        try {
            UserOutputDTO userOutputDTO = userInputPort.createUser(user);

            return new ResponseEntity<>(userOutputDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserOutputDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO user) {
        try {
            UserOutputDTO userOutputDTO = userInputPort.updateUser(id, user);

            return new ResponseEntity<>(userOutputDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserOutputDTO>> getAllUsers() {
        try {
            List<UserOutputDTO> users = userInputPort.getAllUsers();

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<UserOutputDTO> getUserById(@PathVariable Long id) {
        try {
            UserOutputDTO user = userInputPort.getUserById(id);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        try {
            String msj = userInputPort.deleteUser(id);

            return new ResponseEntity<>(msj, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
