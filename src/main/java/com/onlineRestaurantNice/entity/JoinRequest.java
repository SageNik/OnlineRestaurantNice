package com.onlineRestaurantNice.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Ник on 20.06.2018.
 */
@Data
//@Entity
@Table(name = "join_request")
public class JoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User joinUser;
    private Long ownerGroup_id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public JoinRequest(User joinUser, Group group) {
        this.joinUser = joinUser;
        this.ownerGroup_id = group.getOwner_id();
        this.group = group;
    }
    public JoinRequest(){}
}
