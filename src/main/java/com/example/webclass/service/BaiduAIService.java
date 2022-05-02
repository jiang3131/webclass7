package com.example.webclass.service;

import com.baidu.aip.face.AipFace;
import com.example.webclass.config.BaiduAiProperties;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BaiduAIService {
    @Autowired
    BaiduAiProperties baiduAiProperties;

    public JSONObject checkFaces(String imageBase64){
        // 传入可选参数调用接口
        HashMap options = new HashMap();
        options.put("face_field", "age,beauty,expression,face_shape,gender,glasses,landmark,landmark72,landmark150,quality,eye_status,emotion,face_type");
        options.put("max_face_num", "10");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "NONE");
        String imageType = "BASE64";
        // 人脸检测
        AipFace client = new AipFace(baiduAiProperties.getAppId(), baiduAiProperties.getApiKey(), baiduAiProperties.getSecretKey());
        JSONObject res = client.detect(imageBase64, imageType, options);
        return res;

    }
    public JSONObject checkFaceWithUrl(String url){

        HashMap<String, Object> options = new HashMap<>();
        options.put("face_field", "age,beauty,expression,gender,glasses,emotion,face_type,face_token");
        options.put("max_face_num", "10");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "NONE");

        String imageUrl = url;
        String imageType = "URL";
        AipFace client = new AipFace(baiduAiProperties.getAppId(), baiduAiProperties.getApiKey(), baiduAiProperties.getSecretKey());
        JSONObject res = client.detect(imageUrl, imageType, options);
        return res;
    }

}
