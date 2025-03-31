package com.demo.maintenance.bean;

public class UserEntity {
    public Integer code;
    public String msg;
    public DataDTO data;

    public static class DataDTO {
        public Long id;
        public String username;
        public String realName;
        public String headUrl;
        public String mobile;
    }
}
