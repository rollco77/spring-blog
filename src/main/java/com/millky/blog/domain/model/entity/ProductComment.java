package com.millky.blog.domain.model.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * packageName  : com.millky.blog.domain.model.entity
 * fileName     : ProductComment
 * author       : suhwan
 * date         : 2022/08/18
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/08/18         suhwan        최초생성
 */
public class ProductComment {

    @Id
    @GeneratedValue
    private int id;
}
