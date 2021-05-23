package com.example.firedetect.model;

import lombok.Data;

@Data
public class StaticDto {
    private String tmp;
    private String smoke;

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getSmoke() {
        return smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke = smoke;
    }
}
