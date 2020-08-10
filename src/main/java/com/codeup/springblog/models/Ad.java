package com.codeup.springblog.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false, unique = true)
    private String title;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String description;

    @OneToMany(mappedBy = "parentAd")
    @JsonManagedReference
    private List<Comment> comments;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name="ad_tag",
//            joinColumns = {@JoinColumn(name="ad_id")},
//            inverseJoinColumns = {@JoinColumn(name="tag_id")}
//    )
//    private List<Tag> tags;

    public Ad(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
//    show create table ads;
}
//    CREATE USER spring_user@localhost IDENTIFIED BY 'password';
//        GRANT ALL ON spring_db.* TO spring_user@localhost;