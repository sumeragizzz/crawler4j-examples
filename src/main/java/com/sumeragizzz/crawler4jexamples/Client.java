package com.sumeragizzz.crawler4jexamples;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Client {

    public static void main(String[] args) {
        String crawlStorageFolder = System.getProperty("CrawlStorageFolder");
        if (crawlStorageFolder == null || crawlStorageFolder.isEmpty()) {
            throw new IllegalArgumentException("CrawlStorageFolder");
        }

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setIncludeHttpsPages(true);
        config.setMaxDepthOfCrawling(0);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        controller.addSeed("https://www.apple.com/jp/");

        controller.start(SampleCrawler.class, 1);
    }

}
