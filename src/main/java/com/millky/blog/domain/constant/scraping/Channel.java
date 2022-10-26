package com.millky.blog.domain.constant.scraping;

/**
 * packageName  : com.millky.blog.domain.constant.scraping
 * fileName     : Channel
 * author       : suhwan
 * date         : 2022/10/18
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/10/18         suhwan        최초생성
 */
public enum Channel {

    NV("네이버쇼핑");

    private String value;
    Channel(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
