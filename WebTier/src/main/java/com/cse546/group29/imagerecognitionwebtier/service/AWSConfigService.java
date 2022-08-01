package com.cse546.group29.imagerecognitionwebtier.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

public class AWSConfigService {

    @Value("${spring.profiles.active.ACCESSKEY}")
    private String ACCESSKEY;

    @Value("${spring.profiles.active.SECRET_ACCESSKEY}")
    private String SECRET_ACCESSKEY;

    public String getACCESSKEY() {
        return ACCESSKEY;
    }

    public String getSECRET_ACCESSKEY() {
        return SECRET_ACCESSKEY;
    }

    public void setACCESSKEY(String ACCESSKEY) {
        this.ACCESSKEY = ACCESSKEY;
    }

    public void setSECRET_ACCESSKEY(String SECRET_ACCESSKEY) {
        this.SECRET_ACCESSKEY = SECRET_ACCESSKEY;
    }
}
