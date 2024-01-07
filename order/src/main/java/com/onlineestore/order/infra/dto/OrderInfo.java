package com.onlineestore.order.infra.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInfo {
    private String id;
    private UserDTO user;
    private ProductDTO product;
    private BigDecimal price;
    private int quantity;
}
