package com.example.webclass.apis;


import cn.edu.njuit.web.server.tools.ImageTool;
import com.example.webclass.dto.ValidateCode;
import com.example.webclass.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class ValidateCodeController{
    @Autowired
    ValidateCodeService validateCodeService;

    @GetMapping("/api/validate-code")
    protected ResponseEntity<ValidateCode> code()  {
       //产生验证码
        //将验证码返回
        return ResponseEntity.ok(validateCodeService.createValidate());
    }

    @GetMapping("/image/validate-code")
    protected void doGet(String code, HttpServletResponse response) throws ServletException, IOException {
        //解析dode参数
        //调用验证码服务器获得算式文本
        ValidateCodeService validateCodeService = new ValidateCodeService();
        String imagetext = validateCodeService.getValidateText(code);
        if(imagetext!=null){
            //调用字符串生成的方法
            ImageTool imageTool = new ImageTool();
            Integer width = imagetext.length()*16;
            Integer height = 40;
            ByteArrayOutputStream image = imageTool.string2Image(imagetext, width, height);
            response.setContentType("image/jpeg");
            image.writeTo(response.getOutputStream());
        }
    }

}
