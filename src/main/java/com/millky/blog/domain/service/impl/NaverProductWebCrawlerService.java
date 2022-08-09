package com.millky.blog.domain.service.impl;

import com.millky.blog.domain.service.WebCrawlerService;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

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

    public void productSearchCrawl(String productName) {

        System.setProperty("webdriver.chrome.driver", "/Users/suhwan/work/pythonwork/chromedriver");
        //Chrome
        ChromiumDriver webDriver = new ChromeDriver();
        webDriver.get("https://shopping.naver.com/home/p/index.naver");
        Duration duration = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(webDriver, duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form[name=search]")));

        WebElement searchForm  = webDriver.findElement(By.cssSelector("form[name=search]"));
        WebElement searchInput = searchForm.findElement(By.cssSelector("input[type=text]"));
        searchInput.sendKeys("공기청정기");
        //WebElement searchBotton = searchForm.findElement(By.cssSelector("button[type=button]:eq(2)"));
        List<WebElement> searchBottons = searchForm.findElements(By.cssSelector("button[type=button]"));

        //searchBotton.get
        searchBottons.get(1).click();

        wait = new WebDriverWait(webDriver, duration);
        wait.until( ExpectedConditions.elementToBeClickable(By.linkText("네이버 이용약관")));
        webDriver.get(webDriver.getCurrentUrl());
        try {
           // wait.wait(2000);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //webDriver.close();
        //List<WebElement> itemListDivs  = webDriver.findElements(By.cssSelector(".list_basis > div > div"));
        //List<WebElement> itemListDivs  = webDriver.findElements(By.xpath("/html/body/div/div/div[2]/div[3]/div/ul/div/div/"));
       // WebElement itemListDiv  = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[3]/div/ul/div/div"));

        WebElement itemListDiv  = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[3]/div/ul/div"));

        List<WebElement> itemListDivs  = itemListDiv.findElements(By.xpath("./div"));

        log.info("itemListDivs size:" + itemListDivs.size());
        for( WebElement divElementObj : itemListDivs){
            WebElement alink = divElementObj.findElement(By.cssSelector("a[class^='basicList_link']"));
            String href = alink.getAttribute("href");
            String title = alink.getAttribute("title");
            log.info("[title:{}] href:{}", title, href);

            //openDetailProductPage(href);
            sleep(2);
            //webDriver.executeScript("arguments[0].scrollIlntoView(true);", alink);
        }

        //WebDriverWait wait = new WebDriverWait(webDriver, duration);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));

       // Duration duration = Duration.ofSeconds(10);


      //  WebElement searchBar = webDriver.findElement(By.cssSelector("#lst-ib"));
       // searchBar.sendKeys("어쩌다, 블로그");
       // webDriver.findElement(By.cssSelector("#_fZl")).click();

        //ExpectedConditions.elementToBeClickable(By.linkText("어쩌다, 블로그"));

       // WebElement bkim = new WebDriverWait(webDriver, duration).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));


       // bkim.click();
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


    public void productReviewCrawl() {

        System.setProperty("webdriver.chrome.driver", "/Users/suhwan/work/pythonwork/chromedriver");
        //Chrome
        ChromiumDriver webDriver = new ChromeDriver();
        Duration duration = Duration.ofSeconds(3);
        webDriver.get("https://search.shopping.naver.com/catalog/25836450522?query=%EA%B3%B5%EA%B8%B0%EC%B2%AD%EC%A0%95%EA%B8%B0&NaPm=ct%3Dl6g9vb0g%7Cci%3Dbe3e84698db79149d6b6e9b31ab55a8eb2df85bf%7Ctr%3Dslsl%7Csn%3D95694%7Chk%3D32b56f35640984c752daf6cf7d71acc69faabeb7");

        String shoppingmall_review ="/html/body/div/div/div[2]/div[2]/div[2]/div[3]/div[6]/ul";
        WebDriverWait wait = new WebDriverWait(webDriver, duration);
        try{
            wait.wait();
        }catch(Exception e){

        }
        List<WebElement> reviews = webDriver.findElements(By.cssSelector("p[class^='reviewItems_text']"));

        log.info("reviewSize : "+ reviews.size());
        for( WebElement reviewObj : reviews){
            String strReview = reviewObj.getText();
            log.info("strReview : {}", strReview);

        }

    }
}
