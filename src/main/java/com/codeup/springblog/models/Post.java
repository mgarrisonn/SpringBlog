package com.codeup.springblog.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String post;

    @OneToMany(mappedBy = "parentPost")
    private List<Comment> comments;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "post_tag",
            joinColumns = {@JoinColumn(name="post_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private List<Tag> tags;

    public Post(){}

    public Post(long id, String title, String post){
        this.id = id;
        this.title = title;
        this.post = post;
    }

    public Post(Long id, String title, String post, User author, List<Image> images){
        this.id = id;
        this.title = title;
        this.post = post;
        this.author = author;
        this.images = images;

    }

    public Post(List<Comment> comments, List<Image> images, User author, List<Tag> tags) {
        this.comments = comments;
        this.images = images;
        this.author = author;
        this.tags = tags;
    }

    public String getTitle(){
        return this.title;
    }

    public String getPost(){
        return this.post;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public void setPost(String newPost){
        this.post = newPost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
