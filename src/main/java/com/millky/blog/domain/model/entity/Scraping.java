package com.millky.blog.domain.model.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * packageName  : com.millky.blog.domain.model.entity
 * fileName     : Scraping
 * author       : suhwan
 * date         : 2022/10/12
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/10/12         suhwan        최초생성
 */

@Entity
@Slf4j
@Data
@Table(name="T_SCRAPING")
public class Scraping {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    //@Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @org.hibernate.annotations.Comment("채널")
    @Column(columnDefinition = "VARCHAR(2)")
    private String channel;

    @org.hibernate.annotations.Comment("스크래핑 할 업체수")
    //@Column(columnDefinition = "VARCHAR(2)")
    private int noc;

    @org.hibernate.annotations.Comment("키워드")
    @Column(columnDefinition = "VARCHAR(100)")
    private String keyword;

    @org.hibernate.annotations.Comment("스크래핑 상태")
    @Column(columnDefinition = "VARCHAR(2)")
    private String status;

    @org.hibernate.annotations.Comment("배치 시작일")
    //@Column(columnDefinition = "VARCHAR(2)")
    private Date startAt;

    @org.hibernate.annotations.Comment("배치 종료일")
    //@Column(columnDefinition = "VARCHAR(2)")
    private Date endAt;

    @org.hibernate.annotations.Comment("설명")
    @Column(columnDefinition = "VARCHAR(500)")
    private String description;

    @org.hibernate.annotations.Comment("등록 일자")
    private Date createdAt;

    @org.hibernate.annotations.Comment("등록자")
    //@Column(columnDefinition = "BINARY(16)")
    private UUID createdBy;

    @org.hibernate.annotations.Comment("수정 일자")
    private Date modifiedAt;

    @org.hibernate.annotations.Comment("수정자")
    //@Column(columnDefinition = "BINARY(16)")
    private UUID modifiedBy;

}
