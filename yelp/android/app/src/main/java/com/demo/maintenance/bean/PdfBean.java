package com.demo.maintenance.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PdfBean {

    @Id(autoincrement = true)
    private Long id;

    /** PDF 文件名称 **/
    private String name;

    /** 创建时间 **/
    private String createTime;

    /** 文件路径 **/
    private String path;

    /** 位置 **/
    private String position;

    /** 文件或者文件夹归属人 **/
    private Long userId;

    /** 类型，0：文件夹，1：文件 **/
    private int type;

    /** 父文件夹 **/
    private long parentId;

    @Generated(hash = 39390798)
    public PdfBean(Long id, String name, String createTime, String path,
            String position, Long userId, int type, long parentId) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.path = path;
        this.position = position;
        this.userId = userId;
        this.type = type;
        this.parentId = parentId;
    }

    @Generated(hash = 108280533)
    public PdfBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getParentId() {
        return this.parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

}
