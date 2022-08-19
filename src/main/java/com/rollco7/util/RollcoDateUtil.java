package com.rollco7.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * packageName  : com.rollco7.util
 * fileName     : RollcoDateUtil
 * author       : suhwan
 * date         : 2022/08/18
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/08/18         suhwan        최초생성
 */
@Slf4j
public class RollcoDateUtil {

    /**
     * 지정된 날짜 시간의 date 객체를 반환한다.
     * @param year 년도
     * @param month 월
     * @param day 일
     * @param hour 시간
     * @param minuite 분
     * @return date object
     */
    public static Date getDateZeroTime(int year,int month, int day, int hour , int minuite ){
        LocalDateTime tempLocalDateTime = LocalDateTime.of(year , month , day , hour , minuite);
        return java.sql.Timestamp.valueOf(tempLocalDateTime);
    }

    public static Date getDateDayTerm(int year,int month, int day, int hour , int minuite , int dayTerm ){
        LocalDateTime tempLocalDateTime = LocalDateTime.of(year , month , day , hour , minuite);
        LocalDateTime returnDateTime    = null;
        if(dayTerm > 0){
            returnDateTime = tempLocalDateTime.plusDays(dayTerm);
        }else {
            returnDateTime = tempLocalDateTime.minusDays(dayTerm * -1);
        }

        return java.sql.Timestamp.valueOf(returnDateTime);
    }

    public static Date getDateZeroTime(String yyyymmdd ) {

        try {

            final String REGEX = "[0-9]+";

            if (!yyyymmdd.matches(REGEX)) {
                return null;
            }
            String year = yyyymmdd.substring(0, 4);
            String month = yyyymmdd.substring(4, 6);
            String day = yyyymmdd.substring(6, 8);

            return RollcoDateUtil.getDateZeroTime(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getPreviousDateZeroTime(String yyyymmdd ) {
        try {

            if (!dateNumCheck(yyyymmdd)) {
                return null;
            }
            String year = yyyymmdd.substring(0, 4);
            String month = yyyymmdd.substring(4, 6);
            String day = yyyymmdd.substring(6, 8);

            return RollcoDateUtil.getDateDayTerm(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0 , -1);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Date now(){
        LocalDateTime localDateTime = LocalDateTime.now();
        return java.sql.Timestamp.valueOf(localDateTime);
    }

    private static boolean dateNumCheck(String yyyymmdd){
        final String REGEX = "[0-9]+";

        if (!yyyymmdd.matches(REGEX)) {
            return false;
        }else{
            return true;
        }
    }
}
