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
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long owner_id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupOrder> groupOrders;

    public Group(Long id, Long owner_id, String name, String description) {
        this.id = id;
        this.owner_id = owner_id;
        this.name = name;
        this.description = description;
        this.users = new ArrayList<>();
        this.groupOrders = new ArrayList<>();
    }

    public Group(){}
}
