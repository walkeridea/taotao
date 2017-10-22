package com.taotao.cart.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.cart.bean.User;
import com.taotao.common.service.ApiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private ApiService apiService;

    @Autowired
    private PropertieService propertieService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public User queryUserByToken(String token){
        String url = propertieService.TAOTAO_SSO_URL+"/service/user/"+token;
        try {
            String jsonData = this.apiService.doGet(url);
            if(StringUtils.isNotEmpty(jsonData)){
                return MAPPER.readValue(jsonData,User.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
