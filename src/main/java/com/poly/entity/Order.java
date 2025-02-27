package com.poly.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)

    private User user;
    private Date order_date;
    private Double total_amount;

    private Integer status_id;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
