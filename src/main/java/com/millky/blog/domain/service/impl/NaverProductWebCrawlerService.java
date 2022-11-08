package com.millky.blog.domain.service.impl;

import com.millky.blog.application.utility.StringUtility;
import com.millky.blog.domain.constant.scraping.ScrapingStatus;
import com.millky.blog.domain.dto.userapi.ainaver.req.SentimentRequestDto;
import com.millky.blog.domain.dto.userapi.ainaver.req.TextSummaryRequestDto;
import com.millky.blog.domain.dto.userapi.ainaver.res.SentimentResponseDto;
import com.millky.blog.domain.dto.userapi.ainaver.res.TextSummaryResponseDto;
import com.millky.blog.domain.model.entity.Product;
import com.millky.blog.domain.model.entity.ProductReview;
import com.millky.blog.domain.model.entity.Scraping;
import com.millky.blog.domain.model.vo.NaverProdoctSelector;
import com.millky.blog.domain.repository.ProductRepository;
import com.millky.blog.domain.repository.ScrapingRepository;
import com.millky.blog.domain.service.WebCrawlerService;
import com.millky.blog.domain.userapi.AiNaverService;
import com.rollco7.util.NumberUtil;
import com.rollco7.util.RollcoDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
//import org.openqa.selenium.chromium.ChromiumOptions;
//import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.URL;
import java.time.Duration;
import java.util.*;

/**
 * packageName  : com.millky.blog.domain.service.impl
 * fileName     : WebCrawlerService
 * author       : suhwan
 * date         : 2022/08/04
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/08/04         suhwan        최초생성
 */
@Service("naverProductWebCrawlerService")
@Slf4j
public class NaverProductWebCrawlerService implements WebCrawlerService {

    public static boolean moreLog;
    public static boolean chromeHeadLess;

    private static final String CHROME_WEB_DRIVER_PROP_NAME   = "webdriver.chrome.driver";
    private static final String NAVER_SHOPPING_START_PAGE_URL = "https://shopping.naver.com/home/p/index.naver";


    static{
        moreLog        = false;
        chromeHeadLess = false;
    }

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ScrapingRepository scrapingRepository;

    @Autowired
    AiNaverService aiNaverService;

    @Value("${server.os.type}")
    private String osType;

    @Value("${server.os.chromedriver.path}")
    private String chromedriverPath;

    @Value("${crawler.naver.domains.smartStore}")
    private String DOMAIN_SMARTSTORE;

    @Value("${crawler.naver.domains.searchShopping}")
    private String DOMAIN_SEARCHSHOPPING;



    public List<Scraping> findScrapingAll(){
        return scrapingRepository.findScrapingAll();
    }

    public Scraping insertScraping(Scraping scraping){
        if(scraping.getCreatedAt() == null){
            scraping.setCreatedAt(new Date());
        }
        return scrapingRepository.createScraping(scraping);
    }

    public Scraping saveScraping(Scraping scraping){
        return scrapingRepository.saveScraping(scraping);
    }


    public List<Product> findAllByScrapingId(UUID scrapingId){
        return scrapingRepository.findProductAllByScrapingId(scrapingId);
    }

    public Scraping findScraping(UUID scrapingId){
        return scrapingRepository.findScrapingById(scrapingId);
    }

    public Product findByProductId(int product){
        return scrapingRepository.findPrductById(product);
    }

    public List<ProductReview> findProductReviewAllByProductId(int product){
        return scrapingRepository.findProductReviewAllByProductId(product);
    }

    //public List<ProductReview>

    /**
     * 상품검색 후 상품정보 등록
     * @param  scraping 수집정보
     */

    @Override
    public void scraping(Scraping scraping){

        if(scraping == null){
            scraping.setStatus(ScrapingStatus.P1);
            scraping = scrapingRepository.createScraping(scraping);
        }else{
            scraping.setStatus(ScrapingStatus.P1);
            scraping = scrapingRepository.saveScraping(scraping);
        }

        UUID scrapingId = scraping.getId();
        productSearchCrawl(scraping);

        scraping.setStatus(ScrapingStatus.P2);
        scraping = scrapingRepository.saveScraping(scraping);

        List<Product> productList = scrapingRepository.findProductAllByScrapingId(scrapingId);

        for(Product product : productList){
            productReviewCrawl(product);
            product.setCollectCommentYn("Y");
            scrapingRepository.saveProduct(product);
        }

        scraping.setStatus(ScrapingStatus.P3);
        scraping = scrapingRepository.saveScraping(scraping);

    }

    public void productSearchCrawl(Scraping scraping) {

        System.setProperty(CHROME_WEB_DRIVER_PROP_NAME, chromedriverPath);
        //Chrome

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(chromeHeadLess);      //headless=true 인 경우 크롬 창을 띄우지 않는다.
        ChromiumDriver webDriver = new ChromeDriver(options);
        webDriver.get(NAVER_SHOPPING_START_PAGE_URL);
        Duration duration  = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(webDriver, duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form[name=search]")));

        WebElement searchForm  = webDriver.findElement(By.cssSelector("form[name=search]"));
        WebElement searchInput = searchForm.findElement(By.cssSelector("input[type=text]"));
        searchInput.sendKeys(scraping.getKeyword());
        //WebElement searchBotton = searchForm.findElement(By.cssSelector("button[type=button]:eq(2)"));
        List<WebElement> searchBottons = searchForm.findElements(By.cssSelector("button[type=button]"));

        //searchBotton.get
        searchBottons.get(1).click();

        wait = new WebDriverWait(webDriver, duration);
        wait.until( ExpectedConditions.elementToBeClickable(By.linkText("네이버 이용약관")));
        webDriver.get(webDriver.getCurrentUrl());
        try {
           // wait.wait(2000);
            Thread.sleep(2000);
            JavascriptExecutor js = webDriver;
            //웹 페이지를 끝까지 스크롤합니다. //selenium scroll controll 참조 pate https://www.guru99.com/scroll-up-down-selenium-webdriver.html#2
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //webDriver.close();
        //List<WebElement> itemListDivs  = webDriver.findElements(By.cssSelector(".list_basis > div > div"));
        //List<WebElement> itemListDivs  = webDriver.findElements(By.xpath("/html/body/div/div/div[2]/div[3]/div/ul/div/div/"));
        //WebElement itemListDiv  = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[3]/div/ul/div/div"));

        WebElement itemListDiv  = null;
        try{
            itemListDiv  = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[3]/div/ul/div"));
        }catch(Exception e){
            itemListDiv  = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/div/ul/div"));
        }

        List<WebElement> itemListDivs  = itemListDiv.findElements(By.xpath("./div"));

        log.info("itemListDivs size:" + itemListDivs.size());

        WebElement alink                = null;
        WebElement advertisementElement = null;
        WebElement productMallElement   = null;

        String advertisementText        = null;

        int loopCount = 0;

        //TODO 수집상태 수집중으로 변경

        for( WebElement divElementObj : itemListDivs){

            if(loopCount >= 5){
                break;
            }
            loopCount++;

            try{
                advertisementElement = divElementObj.findElement(By.cssSelector("button[class^='ad_ad_stk']"));

                log.debug("!! advertis element");

            }catch(NoSuchElementException ne ){
                log.debug("not found advertis");
                advertisementElement = null;
            }

            //광고 항목 제외
            if(advertisementElement != null){
                advertisementText    = advertisementElement.getText();
                if("광고".equals(advertisementText)){
                    loopCount--;
                    continue;
                }
            }else{
                advertisementText    = "";
            }

            alink              = divElementObj.findElement(By.cssSelector("a[class^='basicList_link']"));
            String href        = alink.getAttribute("href");
            String title       = alink.getAttribute("title");

            productMallElement = divElementObj.findElement(By.cssSelector("a[class^='basicList_mall']"));
            String mallName    = productMallElement.getText();

            //log.info("[title:{}] href:{}", title, href);
            //openDetailProductPage(href);
            //sleep(2);
            //webDriver.executeScript("arguments[0].scrollIlntoView(true);", alink);

            Product product = new Product();
            product.setScrapingId(scraping.getId());
            product.setScraping(scraping);

            product.setRegDate(new Date());
            product.setTitle(title);
            product.setHref(href);
            product.setSearchKeyword(scraping.getKeyword());
            product.setMallName(mallName);

            productRepository.createProduct(product);
        }

        //TODO 수집상태 수집완료 변경
        webDriver.close();
    }

    private void openDetailProductPage(String url){
        ChromiumDriver webDriver = new ChromeDriver();
        webDriver.get(url);
    }

    private void sleep(int second){
        try {
            Thread.sleep(1000*second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void productReviewCrawl(Product product) {

        //String url   = "https://search.shopping.naver.com/catalog/32152670618?query=%EA%B3%B5%EA%B8%B0%EC%B2%AD%EC%A0%95%EA%B8%B0&NaPm=ct%3Dl8jknjpk%7Cci%3Dc20da9d24cfd7d47067b9db861acdd14fce8db2a%7Ctr%3Dslsl%7Csn%3D95694%7Chk%3Dc312841b1544d91d4cdf3d8d19e32140f9a2bad4";
        String url   = "https://smartstore.naver.com/chois_driedfish/products/5078838516?NaPm=ct%3Dla4vo68g%7Cci%3D634c4604c683c19a09e73d51674370847fb0096c%7Ctr%3Dslsl%7Csn%3D1811697%7Chk%3D630112e754d3101d31855f2f67702c30b9155637";
        //String url   = "https://search.shopping.naver.com/catalog/21812315770?adId=nad-a001-02-000000164970440&channel=nshop.npla&query=%EA%B3%B5%EA%B8%B0%EC%B2%AD%EC%A0%95%EA%B8%B0&NaPm=ct%3Dl842isz4%7Cci%3D0HrQ0e0s42fxSb2owflr%7Ctr%3Dpla%7Chk%3Dbe841d6f305f33fdf741ec779883f44b8e08060d&cid=0HrQ0e0s42fxSb2owflr";

        //String url = "https://search.shopping.naver.com/catalog/31692999942?query=%EC%95%84%EC%9B%83%EB%8F%84%EC%96%B4&NaPm=ct%3Dl96bi5nc%7Cci%3D902a07df3ec7af19f3761ee39b516b8c8d8016f0%7Ctr%3Dslsl%7Csn%3D95694%7Chk%3D968692b583e356bde9eff72f4e99ba560e197614";

        if(product != null){
            url = product.getHref();
        }else{
            product = new Product();
            product.setHref(url);
        }

        String domain   = "";

        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        //Chrome

        HashMap<String, Object> images = new HashMap<String, Object>();
        images.put("images", 2);

        HashMap<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings", images);
        //prefs.put("profile.managed_default_content_settings.images", 2);
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(chromeHeadLess);
        //options.setExperimentalOption("prefs", prefs);
        options.addArguments("--blink-setting=imagesEnable=false"); // 페이지 로딩에서 이미지 제외
        ChromiumDriver webDriver = new ChromeDriver(options);
        //ChromiumDriver webDriver = new ChromeDriver();
        //Duration duration        = Duration.ofSeconds(3); //wait 옵션에 사용하려 했으나 적용이 안되는듯.
        //webDriver.get("https://search.shopping.naver.com/catalog/25836450522?query=%EA%B3%B5%EA%B8%B0%EC%B2%AD%EC%A0%95%EA%B8%B0&NaPm=ct%3Dl6g9vb0g%7Cci%3Dbe3e84698db79149d6b6e9b31ab55a8eb2df85bf%7Ctr%3Dslsl%7Csn%3D95694%7Chk%3D32b56f35640984c752daf6cf7d71acc69faabeb7");
        webDriver.get(url);
        webDriver.getCurrentUrl();
        //String shoppingmall_review = "/html/body/div/div/div[2]/div[2]/div[2]/div[3]/div[6]/ul";

        try {
            domain = new URL(webDriver.getCurrentUrl()).getHost().toString();
            log.debug("#### domain for product detail page :{} ",domain);
        } catch (Exception e) {
            log.debug("#### ouccur exception by open url with chromedriver. url :{} ",url);
        }

        NaverProdoctSelector selector  = new NaverProdoctSelector(domain, DOMAIN_SMARTSTORE,DOMAIN_SEARCHSHOPPING);
        log.debug("selector.getReviewTopicDivision():" + selector.getReviewTopicDivision());
        sleep(2);
        //리뷰 주제 element를 가져온다.
        List<WebElement> reviewTopics  =  webDriver.findElements(By.cssSelector(selector.getReviewTopicDivision()));

        String topicName  = "";

        //TODO test용 Product . DB에서 상품정보 조회하여 저장하도록 수정

        product.setStoreType(domain);
        product.setTitle(product.getTitle());
        product.setRegDate(new Date());


        try{

            if(DOMAIN_SEARCHSHOPPING.equals(domain)){
                //주제(topic) 이 더보기가 있는경우 클릭
                if(existElementByCssSeletor(webDriver.findElement(By.cssSelector("div[class^=review_section_review]")),"div[class^=filter_sub_list_box] a[role=button]")){
                    if(existElementByCssSeletor(webDriver,"div[class^=filter_sub_list_box] a[role=button][class^=filter_btn_more]")){
                        WebElement moreTopicButtonElement = webDriver.findElement(By.cssSelector("div[class^=filter_sub_list_box] a[role=button][class^=filter_btn_more]"));
                        moreTopicButtonElement.click();
                        sleep(1);
                    }

                }

                for(WebElement webElement : reviewTopics ){
                    //WebElement topicElement = webElement.findElement(By.cssSelector("a"));

                    //TODO search , smartstore 구분하여 처리
                    getSearchStoreReview(webDriver , webElement , product );
                    log.debug(topicName);
                }
            }else if(DOMAIN_SMARTSTORE.equals(domain)){

                //reviewTopics = webDriver.findElements(By.cssSelector("div[id=REVIEW] div[class^=eg-flick-camera] button"));

                for(WebElement webElement : reviewTopics ){
                    //WebElement topicElement = webElement.findElement(By.cssSelector("a"));

                    //TODO search , smartstore 구분하여 처리
                    getSmartStoreReview(webDriver , webElement , product);
                    log.debug(topicName);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error(Arrays.toString(e.getStackTrace()));
        }finally {
            webDriver.close();
        }
    }

    public void getSmartStoreReview(WebDriver webDriver , WebElement topicElement , Product product){
        log.debug(topicElement.getText());

        String topicName = topicElement.getText().replaceAll("#","");
        log.info("[topic name] : {}" , topicName);

        //JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //js.executeScript("arguments[0].scrollIntoView();", topicElement);

        try{
            topicElement.click();
        }catch(Exception e){
            topicElement.sendKeys(Keys.ENTER);
        }
        sleep(1);

        //리뷰 영역전체를
        WebElement reviewDivision    = webDriver.findElement(By.cssSelector("div[id=REVIEW] > div > div:nth-of-type(3) > div:nth-of-type(2) > ul"));
        WebElement pagingElement     = null;
        List<WebElement> pageButtons = null;
        String pageNum               = "";

        WebElement pagingDivision    = webDriver.findElement(By.cssSelector("div[id=REVIEW] > div > div:nth-of-type(3) > div:nth-of-type(2) > div > div"));
        List<WebElement> pages       = pagingDivision.findElements(By.cssSelector("a[role=button]"));

        //startStore 는 page number가 고정적이지 않고 계속 변동이 되어

        int pageCount = 0;
        if(pages.size() > 0 ){
            for(WebElement pageButtonEl : pages){
                pageCount++;

                //1page 인 경우 페이징 클릭 안하고 댓글 수집
                if(pageCount == 1 ){

                    //getReviewForPage(webDriver, product, pageNum);
                    getReviewForPageWithSmartStore(webDriver,product,pageNum ,topicName);
                }else{ //1페이지가 아닌 경우 다음 버튼을 클릭하여 페이지 이동
                    boolean isStop = false;

                    while(!isStop){

                        String pageTxt = pageButtonEl.getText();
                        pageCount++;
                        log.warn("  [ pageCount ] :"+ pageCount);

                        if(pageCount == 215){
                            log.debug("215!!!!");
                        }

                        if("다음".equals(pageTxt)) {
                            //continue;
                            // 주제버튼을 클릭한다.
                            try{

                                if(pageButtonEl.isDisplayed() && pageButtonEl.isEnabled()) {
                                    sleep(1);
                                    pageButtonEl.click();
                                    sleep(2);
                                    log.warn("pageButtonEl.isDisplayed():"+ pageButtonEl.isDisplayed());
                                    getReviewForPageWithSmartStore(webDriver,product,pageNum,topicName);
                                }else{
                                    log.warn("pageButtonEl.isDisplayed():"+ pageButtonEl.isDisplayed());
                                    log.warn("pageButtonEl.isEnabled():"+ pageButtonEl.isDisplayed());
                                }

                            }catch(Exception e){
                                e.printStackTrace();
                                //다음버튼 클릭시 에러나면 종료
                                isStop = true;
                            }
                            sleep(1);
                        }else{
                            isStop = true;
                        }
                    }
                }
            }
        }
    }

    /**
     * 네이버 쇼핑 검색 리뷰 수집
     * @param webDriver WebDriver
     * @param topicElement WebElement
     * @param product Product
     * @throws Exception non specific exception
     */
    public void getSearchStoreReview(WebDriver webDriver , WebElement topicElement , Product product ) throws Exception{

        //String topicName = topicElement.findElement(By.cssSelector("a")).getText();

        String topicName = topicElement.getText();
        log.info("[topic name] : {}" , topicName);
        if("주제전체".equals(topicName)) {
            return;
        }

        topicElement.click();
        sleep(1);

        //리뷰 영역전체를
        WebElement reviewDivision    = webDriver.findElement(By.cssSelector("div[id=section_review]"));
        WebElement pagingElement     = null;
        List<WebElement> pageButtons = null;
        String pageNum               = "";


        //리뷰페이징이 있는 경우
        if(existElementByCssSeletor(reviewDivision,"div[class^=pagination_pagination]")){
            pagingElement                = reviewDivision.findElement(By.cssSelector("div[class^=pagination_pagination]"));
            pageButtons                  = pagingElement.findElements(By.cssSelector("a[role=button]"));

            for(WebElement buttonElement : pageButtons){
                pageNum = buttonElement.getText();

                if("1".equals(pageNum.substring(pageNum.length()-1, pageNum.length() ))){
                    //continue;
                    log.debug("[ fistPage start]");
                }else{
                    // 주제버튼을 클릭한다.
                    buttonElement.click();
                }
                sleep(1);
                getReviewForPage(webDriver, product, pageNum , topicName);
            }
        }else{ //리뷰 페이징이 없는 경우
            sleep(2);
            getReviewForPage(webDriver, product, "1" ,topicName);
        }
        //WebElement sectionReview = section_review
    }

    private void getReviewForPage(WebDriver webDriver, Product product, String pageNum ,String topicName) {
        WebElement reDrawReviewDivision  = webDriver.findElement(By.cssSelector("div[id=section_review]"));
        List<WebElement> reviewLis       = reDrawReviewDivision.findElements(By.cssSelector("ul[class^=reviewItems_list_review] li"));
        WebElement averageElement        = null;
        WebElement createDateElement     = null;


        log.info("[ pageNum ] : {} ,  " , pageNum);

        int reviewCount = 0;

        String reviewContent = null;
        String repurchaseYn  = null;   //재구매 여부
        String createDateStr = null;
        Date   reviewCreateDate = null;


        for(WebElement reviewLi : reviewLis.toArray(new WebElement[0])){
            reviewCount++;
            log.info("[ pageNum , reviewCount ] : {} ,  {} " , pageNum, reviewCount);

            String average = null;
            repurchaseYn = "N";
            ProductReview productReview = new ProductReview();
            try{

                if(moreLog) log.debug("[ get review step1 ]");
                averageElement    = reviewLi.findElement(By.cssSelector("div[class^=reviewItems_etc_area] span[class^=reviewItems_average]"));
                createDateElement = reviewLi.findElement(By.cssSelector("div[class^=reviewItems_etc_area] span[class^=reviewItems_etc]:nth-of-type(4)"));
                if(moreLog) log.debug("[ get review step2 ]");
                average           = averageElement.getText();
                reviewCreateDate  = RollcoDateUtil.getDateZeroTime(createDateElement.getText());
                if(moreLog) log.debug("[ get review step3 ]");
                int averagePoint  = NumberUtil.getOlnyInt(average);

                if(moreLog) log.debug("[ get review step4 ]");
                WebElement reviewContentElement = reviewLi.findElement(By.cssSelector("p[class^=reviewItems_text]"));
                if(moreLog) log.debug("[ get review step5 ]");
                reviewContent = reviewContentElement.getText();

                if(reviewContent.startsWith("재구매")){
                    repurchaseYn  = "Y";
                    reviewContent = reviewContent.replace("재구매","");
                }


                if(moreLog) log.debug("[ get review step6 ]");

                if(product != null){
                    //TODO product getId를 하지 않아도 되는지 확인해여 product를 set 할지 getId를 할지 결정
                    productReview.setProduct(product);
                    productReview.setProductId(product.getId());
                }
                productReview.setTopic(topicName);
                productReview.setAveragePoint(averagePoint);
                productReview.setContent(reviewContent);
                productReview.setRegDate(new Date());
                productReview.setRepurchaseYn(repurchaseYn);
                productReview.setReviewCreateDate(reviewCreateDate);
                //
                //  TODO productReview.setRegDate();

                scrapingRepository.createProductReview(productReview);

                if(moreLog) log.debug("[ get review step end ]");
            }catch(Exception ex){
                //reviewLi.findElement(By.cssSelector("div[class^=reviewItems_etc_area]"));
                //averageElement.getText();
                ex.printStackTrace();
                log.error("[ pageNum , reviewCount , productReview] : {} ,  {}  -  {}" , pageNum, reviewCount ,productReview.toString());
            }
            //String average = reviewLi.findElement(By.cssSelector("span[class^=reviewItems_average]"));
            //reviewLi.findElement()
            //String average = reviewLi.findElement(By.cssSelector("span[class^=reviewItems_average]"));
        }
    }

    private void getReviewForPageWithSmartStore(WebDriver webDriver, Product product, String pageNum, String topicName){
        WebElement reDrawReviewDivision  = webDriver.findElement(By.cssSelector("div[id=REVIEW] > div > div:nth-of-type(3) > div:nth-of-type(2) > ul"));
        List<WebElement> reviewLis       = reDrawReviewDivision.findElements(By.cssSelector("li"));

        WebElement reviewContentDiv = null;
        WebElement averageDiv       = null;
        WebElement contentDiv       = null;
        WebElement reviewCreateDateDiv = null;

        ProductReview productReview = null;

        int average = 0;

        String reviewContent = null;    //리뷰 내용
        String repurchaseYn  = null;   //재구매 여부
        Date   reviewCreateDate = null; //리뷰 작성일자

        for(WebElement reviewDiv : reviewLis){

            repurchaseYn = "N";

            try {
                reviewContentDiv = reviewDiv.findElement(By.cssSelector("div > div > div > div > div > div"));
                averageDiv = reviewContentDiv.findElement(By.xpath("div/div[2]/div/em"));
                reviewCreateDateDiv = reviewContentDiv.findElement(By.xpath("div/div[2]/div[2]/span"));

                contentDiv = reviewContentDiv.findElement(By.xpath("div[2]/div"));


                log.debug("average: " + averageDiv.getText());
                log.debug("review : " + contentDiv.getText());

                reviewContent = contentDiv.getText();

                if(reviewContent.startsWith("재구매")){
                    repurchaseYn  = "Y";
                    reviewContent = reviewContent.replace("재구매","");
                }

                productReview = new ProductReview();
                if(product != null){
                    //TODO product getId를 하지 않아도 되는지 확인해여 product를 set 할지 getId를 할지 결정
                    productReview.setProduct(product);
                    productReview.setProductId(product.getId());
                }

                average = NumberUtil.getOlnyInt(averageDiv.getText());
                productReview.setTopic(topicName);
                productReview.setAveragePoint(average);
                productReview.setContent(reviewContent);
                productReview.setRegDate(new Date());
                productReview.setRepurchaseYn(repurchaseYn);
                productReview.setReviewCreateDate(RollcoDateUtil.getDateZeroTime(reviewCreateDateDiv.getText()));
                scrapingRepository.createProductReview(productReview);

                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                //웹 페이지를 끝까지 스크롤합니다. //selenium scroll controll 참조 pate https://www.guru99.com/scroll-up-down-selenium-webdriver.html#2

                js.executeScript("arguments[0].scrollIntoView();", reviewContentDiv);
                //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                //reviewContentDiv.get
            }catch(Exception e){
                e.printStackTrace();
                log.error("get review error!!!!!!!!!!");
            }
        }
        log.info("[ pageNum ] : {} ,  " , pageNum);

    }

    /**
     * web element 존재유무 확인
     * @param element WebElement
     * @param strSelector String selector
     * @return boolean
     */
    private boolean existElementByCssSeletor(WebElement element , String strSelector){
        boolean exist = true;
        try{
            element.findElement(By.cssSelector(strSelector));
        }catch(Exception e){
            exist = false;
        }

        return exist;
    }
    private boolean existElementByCssSeletor(WebDriver webDriver , String strSelector){
        boolean exist = true;
        try{
            webDriver.findElement(By.cssSelector(strSelector));
        }catch(Exception e){
            exist = false;
        }

        return exist;
    }

    public void selectProductTest(){
        List<Product> productList = productRepository.findAllProductByDate("20220820");
        for(Product product : productList){
            log.debug(product.toString());
        }
    }


    /**
     * 상품별 감정분석
     * @param productId 상품id
     */
    @Transactional
    public void sentimentAnalysisProduct(int productId){
        List<ProductReview> productReviews = scrapingRepository.findProductReviewAllByProductId(productId);

        SentimentRequestDto  reqDto = null;
        SentimentResponseDto resDto = null;
        String sentimentResult = null;
        double sentimentScoreNegative ;
        double sentimentScorePositive ;
        double sentimentScoreNeutral  ;


        for(ProductReview productReview : productReviews){
            reqDto = new SentimentRequestDto();
            reqDto.setContent(productReview.getContent());
            resDto = aiNaverService.sentiment(reqDto);
            sentimentResult = resDto.getDocument().getSentiment();
            sentimentScoreNegative = resDto.getDocument().getConfidence().getNegative();
            sentimentScorePositive = resDto.getDocument().getConfidence().getPositive();
            sentimentScoreNeutral  = resDto.getDocument().getConfidence().getNeutral();

            productReview.setSentimentResult(sentimentResult);
            productReview.setSentimentScoreNegative(sentimentScoreNegative);
            productReview.setSentimentScorePositive(sentimentScorePositive);
            productReview.setSentimentScoreNeutral(sentimentScoreNeutral);

            scrapingRepository.saveProductReview(productReview);
        }
    }

    /**
     * 리뷰 요약
     * @param productId 상품id
     */
    @Transactional
    public void textSummaryAnalysisProduct(int productId){
        List<ProductReview> productReviews = scrapingRepository.findProductReviewAllByProductId(productId);

        TextSummaryRequestDto  reqDto = null;
        TextSummaryResponseDto resDto = null;
        String tesxtSummaryResult     = null;

        for(ProductReview productReview : productReviews){

            if(productReview.getContent().length() < 60){
                continue;
            }

            reqDto = TextSummaryRequestDto.builder().document(
                    TextSummaryRequestDto.DocumentRequestDto.builder().content( productReview.getContent()).build())
                    .option(TextSummaryRequestDto.OptionRequestDto.builder().language("ko").model("general").tone("2").summaryCount("1").build()).build();

            resDto = aiNaverService.textSummary(reqDto);
            tesxtSummaryResult = resDto.getSummary();

            productReview.setContentSummary(tesxtSummaryResult);

            scrapingRepository.saveProductReview(productReview);
        }
    }
}
