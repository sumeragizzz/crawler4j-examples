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
        System.out.format("---- shouldVisit() : %s ----%n", referringPage.getWebURL());
        return url.getURL().startsWith("https://sumeragizzz.github.io/crawler4j-examples/") && url.getURL().endsWith("html");
    }

    @Override
    public void visit(Page page) {
        System.out.format("**** visit() : %s ****%n", page.getWebURL());

        System.out.format("####         : %d%n", page.getWebURL().getDepth());
        System.out.format("####         : %d%n", page.getWebURL().getDocid());
        System.out.format("####         : %d%n", page.getWebURL().getParentDocid());

        System.out.format("####         : %s%n", page.getWebURL().getAnchor());
        System.out.format("####         : %s%n", page.getWebURL().getDomain());
        System.out.format("####         : %s%n", page.getWebURL().getParentUrl());
        System.out.format("####         : %s%n", page.getWebURL().getPath());
        System.out.format("####         : %s%n", page.getWebURL().getPriority());
        System.out.format("####         : %s%n", page.getWebURL().getSubDomain());
        System.out.format("####         : %s%n", page.getWebURL().getTag());
        System.out.format("####         : %s%n", page.getWebURL().getURL());

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
