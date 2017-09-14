package com.kun.diy_code.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by jiangkun on 2017/9/12.
 */

public class Article {

    /**
     * id : 942
     * title : 绋嬪簭鍛樺簲鑱樺繀澶囪瘝姹�
     * created_at : 2017-08-28T21:23:01.965+08:00
     * updated_at : 2017-09-11T16:03:39.662+08:00
     * replied_at : 2017-09-11T16:03:39.647+08:00
     * replies_count : 4
     * node_name : 绋嬪簭浜虹敓
     * node_id : 35
     * last_reply_user_id : 5671
     * last_reply_user_login : sion
     * user : {"id":1983,"login":"wztyf","name":"wztyf","avatar_url":"https://diycode.b0.upaiyun.com/user/large_avatar/1983_1495807682.jpg"}
     * deleted : false
     * excellent : false
     * abilities : {"update":false,"destroy":false}
     */

    @JSONField(name = "id")
    private int id;
    @JSONField(name = "title")
    private String title;
    @JSONField(name = "created_at")
    private String created_at;
    @JSONField(name = "updated_at")
    private String updated_at;
    @JSONField(name = "replied_at")
    private String replied_at;
    @JSONField(name = "replies_count")
    private int replies_count;
    @JSONField(name = "node_name")
    private String node_name;
    @JSONField(name = "node_id")
    private int node_id;
    @JSONField(name = "last_reply_user_id")
    private int last_reply_user_id;
    @JSONField(name = "last_reply_user_login")
    private String last_reply_user_login;
    @JSONField(name = "user")
    private User user;
    @JSONField(name = "deleted")
    private boolean deleted;
    @JSONField(name = "deleted")
    private boolean excellent;
    @JSONField(name = "abilities")
    private AbilitiesBean abilities;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getReplied_at() {
        return replied_at;
    }

    public void setReplied_at(String replied_at) {
        this.replied_at = replied_at;
    }

    public int getReplies_count() {
        return replies_count;
    }

    public void setReplies_count(int replies_count) {
        this.replies_count = replies_count;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public int getNode_id() {
        return node_id;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    public int getLast_reply_user_id() {
        return last_reply_user_id;
    }

    public void setLast_reply_user_id(int last_reply_user_id) {
        this.last_reply_user_id = last_reply_user_id;
    }

    public String getLast_reply_user_login() {
        return last_reply_user_login;
    }

    public void setLast_reply_user_login(String last_reply_user_login) {
        this.last_reply_user_login = last_reply_user_login;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isExcellent() {
        return excellent;
    }

    public void setExcellent(boolean excellent) {
        this.excellent = excellent;
    }

    public AbilitiesBean getAbilities() {
        return abilities;
    }

    public void setAbilities(AbilitiesBean abilities) {
        this.abilities = abilities;
    }


    public static class AbilitiesBean {
        /**
         * update : false
         * destroy : false
         */

        private boolean update;
        private boolean destroy;

        public boolean isUpdate() {
            return update;
        }

        public void setUpdate(boolean update) {
            this.update = update;
        }

        public boolean isDestroy() {
            return destroy;
        }

        public void setDestroy(boolean destroy) {
            this.destroy = destroy;
        }
    }
}
