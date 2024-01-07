package com.onlineestore.order.infra.outputAdapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.onlineestore.order.infra.dto.UserDTO;
import com.onlineestore.order.infra.outputport.IUserServicePort;

@FeignClient(name = "user-service")
public interface IUserServiceAdapter extends IUserServicePort {

    @Override
    @GetMapping("/users/detail/{id}")
    public UserDTO getUser(@PathVariable Long id);

}
