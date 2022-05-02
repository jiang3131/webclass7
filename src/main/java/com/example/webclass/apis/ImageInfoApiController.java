package com.example.webclass.apis;

import com.example.webclass.dto.ImageFace;
import com.example.webclass.dto.ImageInfo;
import com.example.webclass.repository.ImageFaceRepository;
import com.example.webclass.repository.ImageInfoRepository;
import com.example.webclass.service.BaiduAIService;
import com.example.webclass.service.BosService;
import com.example.webclass.service.ListService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/image-info")
public class ImageInfoApiController {
    @Autowired
    ListService listService;
    @Autowired
    ImageInfoRepository imageInfoRepository;
    @Autowired
    ImageFaceRepository imageFaceRepository;
    @Autowired
    BosService bosService;
    @Autowired
    BaiduAIService baiduAIService;

    @GetMapping
    public Object get(String imageId) {
        try {
            if (imageId == null) {
                List<ImageInfo> imageInfos = listService.getAll();
                return ResponseEntity.ok(imageInfos);
            } else {
                ImageInfo imageInfo = listService.getImageInfo(imageId);
                if (imageInfo == null) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("error", "未找到");
                    return ResponseEntity.ok(map);
                }
                imageInfo.setFaceCount(imageInfo.getFaces().size());
                return ResponseEntity.ok(imageInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest();
        }
    }

    @PostMapping
    public Object post(
            Part image,
            String login,
            String imageText,
            String publishStatus) {
        try {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setLogin(login);
            imageInfo.setImageText(imageText);
            imageInfo.setPublishStatus(publishStatus);
            String imageName = image.getSubmittedFileName();
            String random = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
            String fileName = random + imageName.substring(imageName.lastIndexOf("."));
            String s = bosService.putObject(image.getInputStream(), fileName);
            imageInfo.setImageUrl(s);
            imageInfo.setPublishTime(LocalDateTime.now());
            imageInfo.setImageSize(22222L);
            //把图片存入数据库

            imageInfo = imageInfoRepository.save(imageInfo);
            //人脸检测
            JSONObject faceJson1 = baiduAIService.checkFaceWithUrl(imageInfo.getImageUrl()).getJSONObject("result");
            String[] names = JSONObject.getNames(faceJson1);
            JSONArray faceJson = faceJson1.getJSONArray(names[1]);
            Map<String, Object> faceMap = faceJson.getJSONObject(0).toMap();
            String age = String.valueOf(faceMap.get("age"));
            String beauty = String.valueOf(faceMap.get("beauty")).substring(0, 2);
            String expression = String.valueOf(faceMap.get("expression"));
            String gender = String.valueOf(faceMap.get("gender"));
            String glasses = String.valueOf(faceMap.get("glasses"));
            String emotion = String.valueOf(faceMap.get("emotion"));
            String face_type = String.valueOf(faceMap.get("face_type"));
            String face_token = String.valueOf(faceMap.get("face_token"));
            String[] facecanshu = {age, beauty, expression, gender, glasses, emotion, face_type, face_token};
            for (int i = 0; i < facecanshu.length; i++) {
                facecanshu[i] = facecanshu[i].replaceAll("}", "").substring(String.valueOf(facecanshu[i]).lastIndexOf("=") + 1);
            }
            ImageFace imageFace = new ImageFace();
            imageFace.setFaceAge(Integer.parseInt(facecanshu[0]));
            imageFace.setFaceBeauty(Integer.parseInt(facecanshu[1]));
            imageFace.setExpression(facecanshu[2]);
            imageFace.setGender(facecanshu[3]);
            imageFace.setGlasses(facecanshu[4]);
            imageFace.setEmotion(facecanshu[5]);
            imageFace.setFaceType(facecanshu[6]);
            imageFace.setFaceToken(facecanshu[7]);
            imageFace.setImage(imageInfo);
            ArrayList<ImageFace> faces = new ArrayList<>();
            faces.add(imageFaceRepository.save(imageFace));
            imageInfo.setFaces(faces);
            imageInfo = imageInfoRepository.save(imageInfo);
            return ResponseEntity.ok(imageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest();
    }


    @DeleteMapping
    public Object doDelete(String token, Integer imageId) {
        String UserLogin = token.split("\\.")[1];
        Optional<ImageInfo> imageInfo = imageInfoRepository.findById(imageId);
        if (imageInfo.get().getLogin().equals(UserLogin)) {
            try {
                imageInfoRepository.deleteById(imageId);
                HashMap<String, String> result = new HashMap<>();
                result.put("data", "ok");
                return ResponseEntity.ok(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.badRequest();
    }
}
//            String sql1 = "insert into ImageFace(face_token,face_age,face_beauty,face_type,emotion,expression,glasses,gender,face_time,image_id) values" +
//                    "( ?,?,?,?,?,?,?,?,?,?)";
//            Object[] canshu2 = {facecanshu[7], Integer.parseInt(facecanshu[0]), Integer.parseInt(facecanshu[1]), facecanshu[6], facecanshu[5], facecanshu[2], facecanshu[4], facecanshu[3], LocalDateTime.now(), imageInfo.getId()};
//            jdbcUtil.execInsertAutoId(sql1, canshu2);