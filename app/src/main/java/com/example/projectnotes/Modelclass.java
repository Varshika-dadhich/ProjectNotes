package com.example.projectnotes;

public class Modelclass {
    String title;
    String desc;
    long id;
    Modelclass(String title,String desc){
        this.title = title;
        this.desc = desc;
    }

    Modelclass(long id,String title,String desc){
        this.id = id;
        this.title = title;
        this.desc = desc;
    }
    Modelclass(){
        // empty constructor
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
