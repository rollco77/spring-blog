package com.rollco7.util;

/**
 * packageName  : com.rollco7.util
 * fileName     : NumberUtil
 * author       : suhwan
 * date         : 2022/09/16
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/09/16         suhwan        최초생성
 */
public class NumberUtil {

    public static int getOlnyInt(String targetStr){

        if(targetStr == null){
            targetStr = "";
        }
        String intStr  = targetStr.replaceAll("[^0-9]","");

        if("".equals(intStr)){
            intStr = "0";
        }
        int result = Integer.parseInt(intStr);
        return result;
    }
}
