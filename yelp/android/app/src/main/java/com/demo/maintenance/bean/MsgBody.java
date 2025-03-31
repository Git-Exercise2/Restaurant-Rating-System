package com.demo.maintenance.bean;



public  class   MsgBody implements java.io.Serializable {

     private MsgType localMsgType;


    public MsgType getLocalMsgType() {
        return localMsgType;
    }

    public void setLocalMsgType(MsgType localMsgType) {
        this.localMsgType = localMsgType;
    }
}
