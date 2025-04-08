package com.demo.maintenance.bean;

import java.util.List;

public class RestaurantBean {
    
    public Integer code;
    public String msg;
    public List<DataDTO> data;

    public static class DataDTO {
        public String id;
        public String name;
        public String address;
        public String latitude;
        public String longitude;
        public String rating;
        public Integer reviewCount;
        public String priceLevel;
        public String categories;
        public String imageUrl;
        public String url;
        public String phone;
        public String createDate;
        public String attr1;
        public Object attr2;
        public Object attr3;
        public Object attr4;
    }
}
