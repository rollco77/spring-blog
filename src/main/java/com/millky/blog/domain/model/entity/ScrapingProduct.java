package com.millky.blog.domain.model.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;


import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

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
@Table(name="T_SCRAPING_PRODUCT")
//@org.hibernate.annotations.Table(comment = "상품", appliesTo = "T_PRODUCT")
public class ScrapingProduct {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.Comment("ID")
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scrapingId", insertable = false, updatable = false)
    @org.hibernate.annotations.Comment("스크랩핑 ID")
    private Scraping scraping;

    private UUID scrapingId;

    @org.hibernate.annotations.Comment("타이틀")
    @Column(length=500)
    private String title;

    @org.hibernate.annotations.Comment("URL 링크")
    @Column(length=1000)
    private String href;

    @org.hibernate.annotations.Comment("검색 키워드")
    @Column(length=50)
    private String searchKeyword;

    @org.hibernate.annotations.Comment("댓글 수집여부")
    @ColumnDefault("'N'")
    private String collectCommentYn;

    @org.hibernate.annotations.Comment("스토어 유형")
    @Column(length=100)
    private String storeType;

    @org.hibernate.annotations.Comment("상품몰 이름")
    @Column(length=50)
    private String mallName;

    @org.hibernate.annotations.Comment("작성 일자")
    private Date regDate;

    @org.hibernate.annotations.Comment("수정 일자")
    private Date modDate;

    //댓글 개수
    @Formula("(SELECT count(1) FROM t_scraping_product_review r WHERE r.product_id = id)")
    private int reviewCount;

    //상품 개수
   // @Formula("(SELECT count(1) FROM t_scraping_product r WHERE r.scraping_id = id)")
   // private int productCount;
}
