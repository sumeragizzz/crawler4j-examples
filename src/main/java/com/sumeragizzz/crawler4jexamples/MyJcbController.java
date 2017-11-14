package com.sumeragizzz.crawler4jexamples;

import java.net.MalformedURLException;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.authentication.AuthInfo;
import edu.uci.ics.crawler4j.crawler.authentication.FormAuthInfo;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class MyJcbController {

    public static void main(String[] args) {
        String crawlStorageFolder = System.getProperty("CrawlStorageFolder");
        if (crawlStorageFolder == null || crawlStorageFolder.isEmpty()) {
            throw new IllegalArgumentException("CrawlStorageFolder");
        }

        String userId = System.getProperty("userId");
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("userId");
        }

        String password = System.getProperty("password");
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("password");
        }

        AuthInfo authInfo;
        try {
            authInfo = new FormAuthInfo(userId, password, "https://my.jcb.co.jp/Login", "userId", "password");
        } catch (MalformedURLException e1) {
            throw new IllegalArgumentException("userId or password");
        }

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setIncludeHttpsPages(true);
        config.setMaxDepthOfCrawling(2);
        config.addAuthInfo(authInfo);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        controller.addSeed("https://my.jcb.co.jp/iss-pc/member/details_inquiry/detailMenu.html");

        controller.start(MyJcbCrawler.class, 1);

        controller.shutdown();
    }

}
