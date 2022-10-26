package com.millky.blog.domain.constant.scraping;

/**
 * packageName  : com.millky.blog.domain.constant.scraping
 * fileName     : ScrapingStatus
 * author       : suhwan
 * date         : 2022/10/26
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/10/26         suhwan        최초생성
 */
public enum ScrapingStatus {

    P1("1","수집요청"),
    P2("2","수집중"),
    P3("3","수집완료"),
    E1("9","수집중 오류");
    private String value;
    private String description;

    ScrapingStatus(String value,String description){
        this.value = value;
        this.description = description;
    }

    public String getName(){
        return name();
    }

    public String getValue(){
        return value;
    }

    public String getDescription(){
        return description;
    }
}
