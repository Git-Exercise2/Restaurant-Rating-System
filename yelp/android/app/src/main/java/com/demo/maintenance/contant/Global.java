package com.demo.maintenance.contant;

import com.demo.maintenance.bean.ChatMessages;
import com.demo.maintenance.bean.RestaurantBean;
import com.demo.maintenance.bean.UserEntity;

public class Global {

    public static String token = "";

    public static String username;

    public static String nickname;

    public static String phone;

    public static String email;

    public static Long userId;

    public static final String ROLE_USER = "user";

    public static final String ROLE_KE_FU = "assistant";

    public static ChatMessages chatMessages = new ChatMessages();

    public static RestaurantBean.DataDTO restaurantDetailBean;

}
