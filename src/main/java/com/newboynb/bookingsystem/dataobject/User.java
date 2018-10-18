package com.newboynb.bookingsystem.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class User {

    @Id
    private String userId;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Date createTime;

    private Date updateTime;
}
