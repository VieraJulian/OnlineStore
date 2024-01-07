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
public class OrderDTO {

    private String id;
    private Long user_id;
    private Long product_id;
    private BigDecimal price;
    private int quantity;
}