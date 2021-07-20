package com.blog.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String title;
    private String content;
    private String image;
    private String category;
    private Date creation_date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;

    public Post(Long id, String title, String content, String image, String category, Date creation_date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.category = category;
        this.creation_date = creation_date;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getGenre() {
        return user;
    }
}
