package com.demo.maintenance.bean;

import java.util.List;

public class TranslateBean {

    public String from;

    public String to;

    public List<Data> trans_result;

    public static class Data{

        public String src;

        public String dst;

    }

}
