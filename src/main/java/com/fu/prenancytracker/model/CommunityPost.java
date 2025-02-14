package com.fu.prenancytracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;


@Entity
public class CommunityPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "Title", nullable = false)
    private String title;

    @NotNull
    @Nationalized
    @Lob
    @Column(name = "Content", nullable = false)
    private String content;

    @NotNull
    @Column(name = "CommentCount", nullable = false)
    private Integer commentCount;

    @Size(max = 255)
    @Column(name = "AttachmentUrl")
    private String attachmentUrl;

    @Size(max = 50)
    @Nationalized
    @Column(name = "Status", length = 50)
    private String status;

    @NotNull
    @ColumnDefault("sysdatetime()")
    @Column(name = "CreatedDate", nullable = false)
    private Instant createdDate;

    public CommunityPost(Integer id, User user, String title, String content, Integer commentCount, String attachmentUrl, String status, Instant createdDate) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.attachmentUrl = attachmentUrl;
        this.status = status;
        this.createdDate = createdDate;
    }

    public CommunityPost() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }
}