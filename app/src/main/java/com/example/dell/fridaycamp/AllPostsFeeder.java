package com.example.dell.fridaycamp;

/**
 * Created by Dell on 7/13/2017.
 */

public class AllPostsFeeder {


    String title, body, img, owner;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public AllPostsFeeder(String title, String body, String img, String owner) {

        this.setTitle(title);

        this.setBody(body);

        this.setImg(img);

        this.setOwner(owner);



    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }






}
