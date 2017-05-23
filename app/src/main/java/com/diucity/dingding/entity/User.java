package com.diucity.dingding.entity;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class User {
    public User(int recycler_id, String auth_token) {
        this.recycler_id = recycler_id;
        this.auth_token = auth_token;
    }

    int recycler_id;
    String auth_token;

    public int getRecycler_id() {
        return recycler_id;
    }

    public void setRecycler_id(int recycler_id) {
        this.recycler_id = recycler_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "recycler_id=" + recycler_id +
                ", auth_token='" + auth_token + '\'' +
                '}';
    }

}
