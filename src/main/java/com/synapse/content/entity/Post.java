package com.synapse.content.entity;

import com.synapse.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    private User creator;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false, length = 1000)
    private String content;

    private LocalDateTime createdAt;

    public Post() {
    }

    public Post(User creator, String type, String content) {
        this.creator = creator;
        this.type = type;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public User getCreator() {
        return creator;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
