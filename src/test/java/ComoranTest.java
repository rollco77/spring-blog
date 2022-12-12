import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

import java.util.List;

/**
 * packageName  : PACKAGE_NAME
 * fileName     : ComoranTest
 * author       : suhwan
 * date         : 2022/12/12
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/12/12         suhwan        최초생성
 */
public class ComoranTest {

    public static void main(String args[]){

        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
        String document = "아이폰 처음사용합니다 연보라 이쁘다는평과 보라를좋와해서 선택했는데 투명케이스를 끼웠는데 그닥 표가 안나요 색상응 so so 기능은 매력적이에요 다음에도 아이폰 사용하고 싶어요 단순한 기능만 사용하지만 아쉬운점은 자판이 느리고 답답해요 나머진 신세계 경험중~";

        KomoranResult analyzeResultList = komoran.analyze(document);

        System.out.println(analyzeResultList.getPlainText());

        List<Token> tokenList = analyzeResultList.getTokenList();
        Token beforeToken = null;
        for(Token token : tokenList ){
            System.out.format("(%2d,%2d) %s/%s\n" , token.getBeginIndex(), token.getEndIndex(),token.getMorph(),token.getPos());
            if(beforeToken!=null){
               // beforeToken.getMorph()
            }

            beforeToken = token;
        }

    }
}
