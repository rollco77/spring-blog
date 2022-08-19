package com.millky.blog.domain.model.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.*;
import java.util.Date;

/**
 * packageName  : com.millky.blog.domain.model.entity
 * fileName     : Product
 * author       : suhwan
 * date         : 2022/08/18
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/08/18         suhwan        최초생성
 */
@Data
@Entity
@Table(name="T_PRODUCT")
public class Product {

    @Id
    @GeneratedValue
    int id;

    @Column(length=500)
    private String title;

    @Column(length=1000)
    private String href;

    @Column(length=50)
    private String searchKeyword;

    private Date regDate;

    @ColumnDefault("'N'")
    private String collectCommentYn;

    @Column(length=100)
    private String storeType;

}
