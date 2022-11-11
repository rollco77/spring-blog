import org.junit.jupiter.api.Test;

/**
 * packageName  : PACKAGE_NAME
 * fileName     : JavaTest
 * author       : suhwan
 * date         : 2022/08/23
 * description  :
 * ==========================================================
 * Date            AUTHOR          NOTE
 * 2022/08/23      suhwan        최초생성
 */
public class JavaTest{

    public JavaTest() {

    }
    @Test
    public static void main(String[] args) {

        for(int i = 0 ; i < 5 ; i++) {
            for(int j = i ; j < 5  ; j++) {
                System.out.print("*");
            }
            System.out.print(" ");
            System.out.print("**");
            System.out.println();
        }
        System.out.print("*");
        System.out.print("***");
        System.out.println("*");

        for(int i = 0 ; i < 5 ; i++) {

            for(int j = 5 ; 5 - i  <= j  ; j--) {
                System.out.print("*");
            }
            System.out.print(" ");
            System.out.print("**");
            System.out.println();
        }
        System.out.println("수고하셨습니다. ^^");
    }
}