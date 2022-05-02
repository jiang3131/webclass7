package com.example.webclass.service;

import com.alibaba.fastjson.util.TypeUtils;
import com.example.webclass.dto.ImageInfo;
import com.example.webclass.repository.ImageInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ListService {
    @Autowired
    ImageInfoRepository imageInfoRepository;

    public ImageInfo getImageInfo(String id) {
        if (id == null) {
            id = "-1";
        }
        Optional<ImageInfo> imageInfo1 = imageInfoRepository.findById(Integer.valueOf(id));
        if (imageInfo1.isPresent()) {
            ImageInfo imageInfo = imageInfo1.get();
            return imageInfo;
        }
        return null;
    }
    public List<ImageInfo> getAll(){
        return imageInfoRepository.findAll();
    }

}
