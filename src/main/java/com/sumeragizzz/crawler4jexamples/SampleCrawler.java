package com.sumeragizzz.crawler4jexamples;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.ParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class SampleCrawler extends WebCrawler {

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        return url.getURL().startsWith("https://www.apple.com/jp/") && url.getURL().endsWith("html");
    }

    @Override
    public void visit(Page page) {
        System.out.format("**** %s - %s ****%n", page.getWebURL().getURL(), page.getRedirectedToUrl());

        HtmlParseData parseData = cast(page.getParseData());
        parseData.getHtml();

        System.out.println("--------");
        System.out.println(parseData.getHtml());

        System.out.println("--------");
        System.out.println(parseData.getText());
    }

    @SuppressWarnings("unchecked")
    private <T> T cast(ParseData parseData) {
        return (T) parseData;
    }
}
