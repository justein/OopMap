package com.nova.lyn.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/11 0011 下午 2:59
 * @Version 1.0
 */
@Data
public class User implements Serializable {

    private String username;
    private String password;
    private int age;
}
