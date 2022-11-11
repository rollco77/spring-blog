package com.millky.blog.domain.model.vo;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

/**
 * packageName  : com.millky.blog.domain.model.vo
 * fileName     : NaverProdoctSelector
 * author       : suhwan
 * date         : 2022/08/29
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/08/29         suhwan        최초생성
 */
@Slf4j
public class NaverProdoctSelector {

    private String DOMAIN_SMARTSTORE;

    private String DOMAIN_SEARCHSHOPPING;

    private String DOMAIN_SHOPPING;

    private String domain;


    public NaverProdoctSelector(String domain , String domainSmartStore , String domainSearchShopping ,String domainShopping){
        this.domain = domain;
        this.DOMAIN_SMARTSTORE = domainSmartStore;
        this.DOMAIN_SEARCHSHOPPING = domainSearchShopping;
        this.DOMAIN_SHOPPING = domainShopping;
    }

    private String reviewTopicDivision;

    public String getReviewTopicDivision(){

        if(this.domain.equals(DOMAIN_SMARTSTORE) || this.domain.equals(DOMAIN_SHOPPING)) {
            reviewTopicDivision = "div[id=REVIEW] div[class^=eg-flick-camera] button[class*=eg-flick-panel]";
        }else if(this.domain.equals(DOMAIN_SEARCHSHOPPING)){
            reviewTopicDivision = "div[class^=review_section_review] div[class^=filter_sort_group] div[class^=filter_evaluation_tap] div[class^=filter_sub_list] a[role=button]";
        }
        return reviewTopicDivision;
    }
}
