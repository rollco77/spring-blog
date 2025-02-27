package com.millky.blog.presentation.controller.web;

import com.millky.blog.domain.model.entity.ScrapingProduct;
import com.millky.blog.domain.model.entity.ScrapingProductReview;
import com.millky.blog.domain.model.entity.Scraping;
import com.millky.blog.domain.service.WebCrawlerService;
import com.rollco7.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * packageName  : com.millky.blog.presentation.controller.web
 * fileName     : ScrapingController
 * author       : suhwan
 * date         : 2022/10/13
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/10/13         suhwan        최초생성
 */
@Controller
@RequestMapping("scraping")
@Slf4j
public class ScrapingController {

    @Autowired
    private WebCrawlerService webCrawlerService;

    @RequestMapping({ "/", "/index" })
    public String index(Model model) {

        return "scraping/index";
    }

    @RequestMapping({ "/scrap/list" })
    public String list(Model model) {

        List<Scraping> scrapinglist =  webCrawlerService.findScrapingAll();
        model.addAttribute("scrapingList", scrapinglist);

        return "scraping/scrapingList";
    }

    @RequestMapping(value="/scrap/insert")
    @ResponseBody
    public HashMap<String, Object> insert(Scraping scraping , Model model) {
        log.info(scraping.toString()   );
       // List<Scraping> scrapinglist =  webCrawlerService.findScrapingAll();
       // model.addAttribute("scrapingList", scrapinglist);
        Scraping resultScraping = webCrawlerService.insertScraping(scraping);

        HashMap<String,Object> hashMap = new HashMap();
        hashMap.put("scraping", resultScraping);

        return hashMap;
    }

    @RequestMapping(value="/scrap/start/{scrapingId}")
    @ResponseBody
    public HashMap<String, Object> startScraping(Scraping scraping ,Model model,@PathVariable String scrapingId) {
        // List<Scraping> scrapinglist =  webCrawlerService.findScrapingAll();
        // model.addAttribute("scrapingList", scrapinglist);

        UUID uuid = UUID.fromString(scrapingId);
        Scraping resultScraping = webCrawlerService.findScraping(uuid);


        resultScraping.setBaseScoreNeutral(scraping.getBaseScoreNeutral());
        resultScraping.setBaseScoreNegative(scraping.getBaseScoreNegative());
        resultScraping.setBaseScorePositive(scraping.getBaseScorePositive());
        webCrawlerService.saveScraping(resultScraping);

        HashMap<String,Object> hashMap = new HashMap();
        hashMap.put("scraping", resultScraping);

        webCrawlerService.asyncScraping(resultScraping);

        return hashMap;
    }

    @RequestMapping( value="/product/list/{scrapingId}" , method = RequestMethod.GET)
        public String productList(Model model,@PathVariable String scrapingId) {

        UUID uuid = UUID.fromString(scrapingId);
            List<ScrapingProduct> productlist =  webCrawlerService.findAllByScrapingId(uuid);
        Scraping scraping = webCrawlerService.findScraping(uuid);

        model.addAttribute("scraping", scraping);
        model.addAttribute("productList", productlist);

        return "scraping/productList";
    }

    @RequestMapping( value="/review/list/{productId}" , method = RequestMethod.GET)
    public String reviewList(Model model,@PathVariable String productId) {

        int int_productId = NumberUtil.getOlnyInt(productId);

        ScrapingProduct product = webCrawlerService.findByProductId(int_productId);
        List<ScrapingProductReview> productReviewlist =  webCrawlerService.findProductReviewAllByProductId(int_productId);
        Scraping scraping = webCrawlerService.findScraping(product.getScrapingId());

        model.addAttribute("scraping", scraping);
        model.addAttribute("product", product);
        model.addAttribute("productReviewList", productReviewlist);
        return "scraping/productReviewList";
    }
    @RequestMapping( value="/sentiment/analysis/{classification}/{id}" , method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> sentiment(Model model,@PathVariable String classification,@PathVariable String id) {

        if("product".equals(classification)){
            int int_productId = NumberUtil.getOlnyInt(id);
            webCrawlerService.sentimentAnalysisProduct(int_productId);
        }
        HashMap<String,Object> hashMap = new HashMap();
        //hashMap.put("scraping", resultScraping);

        //webCrawlerService.scraping(resultScraping);

        return hashMap;
    }

    @RequestMapping( value="/textSummary/analysis/{classification}/{id}" , method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> textSummary(Model model,@PathVariable String classification,@PathVariable String id) {

        if("product".equals(classification)){
            int int_productId = NumberUtil.getOlnyInt(id);
            webCrawlerService.textSummaryAnalysisProduct(int_productId);
        }
        HashMap<String,Object> hashMap = new HashMap();
        //hashMap.put("scraping", resultScraping);

        //webCrawlerService.scraping(resultScraping);

        return hashMap;
    }

}
