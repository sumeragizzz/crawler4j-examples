package com.sumeragizzz.crawler4jexamples;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyJcbCrawler extends WebCrawler {

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        return url.getURL().startsWith("https://my.jcb.co.jp/iss-pc/member/details_inquiry/") && url.getURL().endsWith("html");
    }

    @Override
    public void visit(Page page) {
        super.visit(page);
    }

}
