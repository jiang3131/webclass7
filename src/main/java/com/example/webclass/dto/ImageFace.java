package com.example.webclass.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ImageFace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Basic
    private String faceToken;
    @Basic
    private Integer faceAge;
    @Basic
    private Integer faceBeauty;
    @Basic
    private String faceType;
    @Basic
    private String emotion;
    @Basic
    private String expression;
    @Basic
    private String glasses;
    @Basic
    private String gender;
    @Basic
    private String faceTime;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    @JsonIgnore
    private ImageInfo image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public Integer getFaceAge() {
        return faceAge;
    }

    public void setFaceAge(Integer faceAge) {
        this.faceAge = faceAge;
    }

    public Integer getFaceBeauty() {
        return faceBeauty;
    }

    public void setFaceBeauty(Integer faceBeauty) {
        this.faceBeauty = faceBeauty;
    }

    public String getFaceType() {
        return faceType;
    }

    public void setFaceType(String faceType) {
        this.faceType = faceType;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getGlasses() {
        return glasses;
    }

    public void setGlasses(String glasses) {
        this.glasses = glasses;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFaceTime() {
        return faceTime;
    }

    public void setFaceTime(String faceTime) {
        this.faceTime = faceTime;
    }

    public ImageInfo getImage() {
        return image;
    }

    public void setImage(ImageInfo image) {
        this.image = image;
    }
}
