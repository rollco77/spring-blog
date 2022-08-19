package simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * packageName  : simple
 * fileName     : DateTest
 * author       : suhwan
 * date         : 2022/08/18
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/08/18         suhwan        최초생성
 */
@Slf4j
public class DateTest {

    public static void main(String args []){

    }

    @Test
    public void makeDateTest(){

        Date date = new Date();

        LocalDate localDate = LocalDate.now();
        LocalDate newDate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());


        // 1. LocalDateTime 객체 생성(현재 시간)
        LocalDateTime localDateTime = LocalDateTime.now();
        // 2. LocalDateTime -> Date 변환
        Date date2 = java.sql.Timestamp.valueOf(localDateTime);


        LocalDateTime startLocalDateTime = LocalDateTime.of(2022,8,17,0,0);
        LocalDateTime endLocalDateTime   = LocalDateTime.of(2022,8,18,0,0);

        Date startDate = java.sql.Timestamp.valueOf(startLocalDateTime);
        Date endDate   = java.sql.Timestamp.valueOf(endLocalDateTime);

        log.info(startDate.toString());
        log.info(endDate.toString());

    }

}
