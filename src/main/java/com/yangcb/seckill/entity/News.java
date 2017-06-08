package com.yangcb.seckill.entity;

/**
 * ${DESCRIPTION}
 *
 * @author yangcb
 * @create 2017-06-07 13:37
 **/
public class News {

    private String id;
    private String title;
    private String desc;
    private String query_word;
    private String image;
    private String image_v;
    private int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getQuery_word() {
        return query_word;
    }

    public void setQuery_word(String query_word) {
        this.query_word = query_word;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_v() {
        return image_v;
    }

    public void setImage_v(String image_v) {
        this.image_v = image_v;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
