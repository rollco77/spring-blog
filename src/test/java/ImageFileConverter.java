import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;

/**
 * packageName  : PACKAGE_NAME
 * fileName     : ImageConverter
 * author       : suhwan
 * date         : 2022/09/30
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/09/30         suhwan        최초생성
 */
public class ImageFileConverter {
    public static void main(String [] args){
        try{

            Path file = Paths.get("/Users/suhwan/work/rollco7/private_project/spring-blog-271/spring-blog/2017092711380858_P1.gif");

            // 파일 생성일자 찾기
            FileTime creationTime = (FileTime) Files.getAttribute(file, "creationTime");
            System.out.println(creationTime);

            String inputImage = "/Users/suhwan/work/rollco7/private_project/spring-blog-271/spring-blog/2017092711380858_P1.gif";
            String outputImage = "/Users/suhwan/work/rollco7/private_project/spring-blog-271/spring-blog/test.gif";
            String formatName = "GIF";

            try {

                boolean result = ImageConverter.convertFormat(inputImage,
                        outputImage, formatName);
                if (result) {
                    System.out.println("Image converted successfully.");
                } else {
                    System.out.println("Could not convert image.");
                }
            } catch (IOException ex) {
                System.out.println("Error during converting image.");
                ex.printStackTrace();
            }

        }catch(Exception e){

        }
    }
}
