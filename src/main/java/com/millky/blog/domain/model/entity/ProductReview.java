package com.millky.blog.domain.model.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

/**
 * packageName  : com.millky.blog.domain.model.entity
 * fileName     : ProductReview
 * author       : suhwan
 * date         : 2022/08/30
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/08/30         suhwan        최초생성
 */
@Entity
@Slf4j
@Data
@Table(name="T_PRODUCT_REVIEW")
//@org.hibernate.annotations.Table(comment = "상품 리뷰", appliesTo = "T_PRODUCT_REVIEW")
public class ProductReview {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.Comment("ID")
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    @org.hibernate.annotations.Comment("상품 ID")
    private Product product;

    private int productId;

    @org.hibernate.annotations.Comment("리뷰 주제")
    @Column(length=50)
    private String topic;

    @org.hibernate.annotations.Comment("평점")
    private int averagePoint;

    @Column(length=4000)
    @org.hibernate.annotations.Comment("댓글 내용")
    private String content;

    @Column(length=1)
    @org.hibernate.annotations.Comment("재구매 여부")
    private String repurchaseYn;

    @Column(length=1000)
    @org.hibernate.annotations.Comment("댓글 요약")
    private String contentSummary;

    @org.hibernate.annotations.Comment("리뷰 작성 일자")
    private Date reviewCreateDate;

    @org.hibernate.annotations.Comment("작성 일자")
    private Date regDate;

    @org.hibernate.annotations.Comment("수정 일자")
    private Date modDate;

    @Column(length=10)
    @org.hibernate.annotations.Comment("감정분석 결과")
    private String sentimentResult;

    @ColumnDefault("0")
    @org.hibernate.annotations.Comment("감정분석 부정수치")
    private Double sentimentScoreNegative;

    @ColumnDefault("0")
    @org.hibernate.annotations.Comment("감정분석 긍정수치")
    private Double sentimentScorePositive;

    @ColumnDefault("0")
    @org.hibernate.annotations.Comment("감정분석 중립수치")
    private Double sentimentScoreNeutral;
}
