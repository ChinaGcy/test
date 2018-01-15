package com.sdyk.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.security.PrivateKey;

@DatabaseTable(tableName = "DayNews")
public class DayNews {

//    @DatabaseField(generatedId = true)
//    private int Id;
    @DatabaseField(columnName = "newName")
    private String NewsName;
    @DatabaseField(columnName = "PicUrl",width = 20000)
    private String PicUrl;
    @DatabaseField(columnName = "sendTime")
    private String SendTime;
    @DatabaseField(columnName = "forwardNum")
    private String ForwardNum;
    @DatabaseField(columnName = "commentNum")
    private String CommentNum;
    @DatabaseField(columnName = "zanNum")
    private String ZanNum;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getSendTime() {
        return SendTime;
    }

    public void setSendTime(String sendTime) {
        SendTime = sendTime;
    }

    public String getForwardNum() {
        return ForwardNum;
    }

    public void setForwardNum(String forwardNum) {
        ForwardNum = forwardNum;
    }

    public String getCommentNum() {
        return CommentNum;
    }

    @Override
    public String toString() {
        return "DayNews{" +
                "NewsName='" + NewsName + '\'' +
                ", PicUrl='" + PicUrl + '\'' +
                ", SendTime='" + SendTime + '\'' +
                ", ForwardNum='" + ForwardNum + '\'' +
                ", CommentNum='" + CommentNum + '\'' +
                ", ZanNum='" + ZanNum + '\'' +
                '}';
    }

    public void setCommentNum(String commentNum) {
        CommentNum = commentNum;
    }

    public String getZanNum() {
        return ZanNum;
    }

    public void setZanNum(String zanNum) {
        ZanNum = zanNum;
    }

    public DayNews(){

    }


    public String getNewsName() {
        return NewsName;
    }


    public void setNewsName(String newsName) {
        NewsName = newsName;
    }

}
