package simple;

import com.google.common.net.InternetDomainName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * packageName  : simple
 * fileName     : UrlTest
 * author       : suhwan
 * date         : 2022/08/29
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/08/29         suhwan        최초생성
 */
public class UrlTest {
    public static void main (String args[]){

        //String url = "https://search.shopping.naver.com/catalog/20850829749?query=%EA%B3%B5%EA%B8%B0%EC%B2%AD%EC%A0%95%EA%B8%B0&NaPm=ct%3Dl7e6k9j4%7Cci%3Dd41e86ff9bcce278c6469cde8cc9b91baba11e3a%7Ctr%3Dslsl%7Csn%3D95694%7Chk%3D8409c2e94e8e1d63617b83324e0873dc793c70fe";
        String url = "https://smartstore.naver.com/jjangjh/products/715068916?NaPm=ct%3Dl7e6oxqo%7Cci%3D56317156e14180fd6ecc6a7d769ce08271e62145%7Ctr%3Dslsl%7Csn%3D477686%7Chk%3Df3d8ead5d8cc4dbe9cdc03d81ed36526f340f019";

        try {
            System.out.println(InternetDomainName.from(new URL(url).getHost()).topPrivateDomain().toString());
            System.out.println(InternetDomainName.from(new URL(url).getHost()).topDomainUnderRegistrySuffix().toString());

            System.out.println(new URL(url).getHost().toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test(){
        System.out.println("321".substring("321".length()-1, "321".length() ));
    }
}
