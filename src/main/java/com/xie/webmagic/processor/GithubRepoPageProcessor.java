package com.xie.webmagic.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @Creation Date : 2018-03-30 13:49
 * Email is xie.wenbo@jyall.com
 * Copyright is 金色家园网络科技有限公司
 */
public class GithubRepoPageProcessor implements PageProcessor{

    private Site site = Site.me().setRetrySleepTime(3).setSleepTime(100);

    public void process(Page page) {
        System.out.println(page.getHtml());
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft").thread(5).run();
    }
}
