package com.example.demo.model;

import java.io.Serializable;

/**
 * @author : Wangqi
 * @project_name : demo
 * @description :
 * @date : 2020-12-06 23:56
 **/
public class User implements Serializable {
    private String userId;
    private String age;

    public String getUserId() {
        return userId;
    }

    public void setUserid(String userId) {
        this.userId = userId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
