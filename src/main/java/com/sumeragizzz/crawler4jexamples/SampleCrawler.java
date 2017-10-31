package com.sumeragizzz.crawler4jexamples;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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

        Document document = Jsoup.parse(parseData.getHtml());

        Elements acGnList = document.getElementsByClass("ac-gn-list");
        acGnList.forEach(e -> System.out.println(e.select("li a span").text()));
    }

    @SuppressWarnings("unchecked")
    private <T> T cast(ParseData parseData) {
        return (T) parseData;
    }
}
