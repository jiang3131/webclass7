package com.example.webclass.service;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.example.webclass.config.BosProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class BosService {
    @Autowired
    BosProperties bosProperties;
    //    String ACCESS_KEY_ID="b192b1a6bb8d4bd4b8f8a3575b8ccec0";
//    String SECRET_ACCESS_KEY="5dfb7e1f5b0b4cc2a78318acdf5d35e5";
//    String ENDPOINT = "2633564497.su.bcebos.com";
    BosClientConfiguration config;

    public BosClient getCilent() {
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(bosProperties.getACCESS_KEY_ID(), bosProperties.getSECRET_ACCESS_KEY()));
        config.setEndpoint(bosProperties.getENDPOINT());
        BosClient client = new BosClient(config);
        return client;
    }

    public String putObject(InputStream inputStream, String key) {
        BosClient client = getCilent();
        PutObjectResponse putObjectResponseFromInputStream =
                client.putObject("2633564497", key, inputStream);
        String fileUrl = "https://" + bosProperties.getENDPOINT() + "/2633564497" + "/" + key;

        return fileUrl;
    }

}
