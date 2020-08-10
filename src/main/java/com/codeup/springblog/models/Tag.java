//package com.codeup.springblog.models;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "tags")
//public class Tag {
//    @Id
//    @GeneratedValue
//    private long id;
//
//    private String name;
//
//    public Tag(){}
//
//    @ManyToMany(mappedBy = "tags")
//    private List<Ad> ads;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Ad> getAds() {
//        return ads;
//    }
//
//    public void setAds(List<Ad> ads) {
//        this.ads = ads;
//    }
//}
