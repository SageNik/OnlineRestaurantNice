package com.onlineRestaurantNice.entity;

import com.onlineRestaurantNice.enums.OrderState;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ник on 20.06.2018.
 */
//@Entity
@Data
@Table(name = "group_order")
public class GroupOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    private OrderState state;
    private Double sum;

    @OneToMany(mappedBy = "group_order", cascade = CascadeType.ALL)
    private List<Order> orders;

    public GroupOrder(User owner, Group group) {
        this.owner = owner;
        this.group = group;
        this.state = OrderState.NOT_SENT;
        this.orders = new ArrayList<>();
        this.sum = getCurrentSum();
    }

    public GroupOrder(){}

    public double getCurrentSum(){
        double orderSum = 0;
        for(Order order : orders){
            orderSum += order.getSum();
        }
        return orderSum;
    }
}
