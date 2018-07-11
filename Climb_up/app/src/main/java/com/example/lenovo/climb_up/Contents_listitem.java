package com.example.lenovo.climb_up;

import java.util.Date;

/**
 * Created by lenovo on 2018-01-29.
 */

public class Contents_listitem {

    private String ID;
    private String title;
    private Date write_date;
    private String content;


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getWrite_date() {

        return write_date;
    }


    public void setWrite_date(Date write_date) {
        this.write_date = write_date;

    }

    public Contents_listitem(String ID, String title,String content, Date write_date) {
        this.ID = ID;
        this.title = title;
        this.write_date = write_date;
        this.content = content;
    }

}
