package com.kun.diy_code.model;

/**
 * Created by jiangkun on 2017/9/12.
 */

import com.alibaba.fastjson.annotation.JSONField;

/**
 * user: {
 * id: 1983,
 * login: "wztyf",
 * name: "wztyf",
 * avatar_url: "https://diycode.b0.upaiyun.com/user/large_avatar/1983_1495807682.jpg"
 * }
 */
public class User {

    @JSONField(name = "id")
    String id;

    @JSONField(name = "login")
    String login;

    @JSONField(name = "name")
    String name;

    @JSONField(name = "avatar_url")
    String avatarUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
