package com.example.webclass.dto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ImageInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Integer id;
    @Basic
    String login;
    @Basic
    String imageUrl;
    @Basic
    String imageText;
    @Basic
    LocalDateTime publishTime;
    @Basic
    Long imageSize;
    @Basic
    String publishStatus;
    @Transient
    int faceCount;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "image", cascade = CascadeType.ALL)
    List<ImageFace> faces = new java.util.ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageText() {
        return imageText;
    }

    public void setImageText(String imageText) {
        this.imageText = imageText;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public Long getImageSize() {
        return imageSize;
    }

    public void setImageSize(Long imageSize) {
        this.imageSize = imageSize;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public int getFaceCount() {
        return faceCount;
    }

    public void setFaceCount(int faceCount) {
        this.faceCount = faceCount;
    }

    public List<ImageFace> getFaces() {
        return faces;
    }

    public void setFaces(List<ImageFace> faces) {
        this.faces = faces;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", image_url='" + imageUrl + '\'' +
                ", iamge_text='" + imageText + '\'' +
                ", publish_time=" + publishTime +
                ", image_size=" + imageSize +
                ", publish_status='" + publishStatus + '\'' +
                '}';
    }
}
