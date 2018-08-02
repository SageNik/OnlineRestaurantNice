package com.onlineRestaurantNice.entity;

import com.onlineRestaurantNice.enums.OrderState;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ник on 20.06.2018.
 */
@Data
//@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OrderState orderState;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<Dish> dishes;
    private double sum;

    @ManyToOne
    @JoinColumn(name = "group_order_id")
    private GroupOrder groupOrder;

    public Order(OrderState orderState, User user, List<Dish> dishes, GroupOrder groupOrder) {
        this.orderState = orderState;
        this.user = user;
        this.dishes = dishes;
        this.sum = getCurrentSum();
        this.groupOrder = groupOrder;
    }

    public Order() {}
    public double getCurrentSum(){
        int sum = 0;
        for(Dish dish : dishes){
            sum += dish.getPrice()*100*dish.getAmount();
        }
        return sum/100;
    }
}
