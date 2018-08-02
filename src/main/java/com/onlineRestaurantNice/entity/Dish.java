package com.onlineRestaurantNice.entity;

import com.onlineRestaurantNice.enums.DishType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Ник on 20.06.2018.
 */
@Data
//@Entity
@Table(name = "dish")
public class Dish {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer amount;
    private Long dishCategory_id;
    private DishType dishType;

    public Dish(Long id, String name, String description, Double price, Long dishCategory_id, DishType dishType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.dishCategory_id = dishCategory_id;
        this.amount = 0;
        this.dishType = dishType;
    }

    public Dish(){}
}
