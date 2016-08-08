package com.example.swty_wy.myapplication;

import java.io.Serializable;

/**
 * Created by SWTY-WY on 2016/7/25.
 */
public class UserInfo implements Serializable{
    private String name;
    private int age;

    public UserInfo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
