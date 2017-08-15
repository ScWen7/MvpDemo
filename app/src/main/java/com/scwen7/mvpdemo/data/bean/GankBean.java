package com.scwen7.mvpdemo.data.bean;

import java.util.List;

/**
 * Created by 解晓辉 on 2017/8/15.
 * 作用：
 */

public class GankBean {

    /**
     * _id : 597dce34421aa90ca209c51b
     * createdAt : 2017-07-30T20:16:52.80Z
     * desc : 一个极简但是强大的VR本地播放器，基于IJKPlayer、MD360Player4Android，并使用DataBinding
     * images : ["http://img.gank.io/ea71986c-4e0f-4b21-97a5-06dc311fff0b"]
     * publishedAt : 2017-08-09T13:49:27.260Z
     * source : web
     * type : Android
     * url : https://github.com/wheat7/VRPlayer
     * used : true
     * who : 麦田哥
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
