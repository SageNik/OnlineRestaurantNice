package com.onlineRestaurantNice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ник on 20.06.2018.
 */
@Data
//@Entity
@Table(name = "dish_category")
public class DishCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer amountDish;

    @OneToMany(mappedBy = "dish_category", cascade = CascadeType.ALL)
    private List<Dish> dishes;

    public DishCategory(Long id, String name) {
        this.id = id;
        this.name = name;
        this.amountDish = 0;
        this.dishes = new ArrayList<>();
    }

    public DishCategory(){}

}
