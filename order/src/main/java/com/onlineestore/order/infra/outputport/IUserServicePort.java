package com.onlineestore.order.infra.outputport;

import com.onlineestore.order.infra.dto.UserDTO;

public interface IUserServicePort {

    public UserDTO getUser(Long id);
}
