/**
 * packageName  : PACKAGE_NAME
 * fileName     : NcloudApiTest
 * author       : suhwan
 * date         : 2022/11/02
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/11/02         suhwan        최초생성
 */
public class NcloudApiTest {

    public String makeSignature() {
        String space = " ";					// one space
        String newLine = "\n";					// new line
        String method = "GET";					// method
        String url = "/photos/puppy.jpg?query1=&query2";	// url (include query string)
        String timestamp = "{timestamp}";			// current timestamp (epoch)
        String accessKey = "{accessKey}";			// access key id (from portal or Sub Account)
        String secretKey = "{secretKey}";

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        //SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        //Mac mac = Mac.getInstance("HmacSHA256");
        //mac.init(signingKey);

        //byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        //String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return null;
        //return encodeBase64String;
    }

}
