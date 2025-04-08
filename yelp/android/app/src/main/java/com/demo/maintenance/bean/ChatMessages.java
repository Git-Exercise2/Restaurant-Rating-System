package com.demo.maintenance.bean;

import java.util.List;

public class ChatMessages {

    public List<Data> messages;

    public static class Data {

        public String role;

        public String content;

    }

}
