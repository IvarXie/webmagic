package com.xie.webmagic.processor;

import com.xie.webmagic.pipeline.DoubanPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.stream.Collectors;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @Creation Date : 2018-03-30 14:13
 * Email is xie.wenbo@jyall.com
 * Copyright is 金色家园网络科技有限公司
 */
public class DoubanPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetrySleepTime(3).setSleepTime(100).addHeader("Connection", "keep-alive")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
    private int i = 0;
    /**
     * <div class="comment">
     <h3> <span class="comment-vote"> <span class="votes">1518</span> <input value="1264433859" type="hidden"> <a href="javascript:;" class="j a_show_login" onclick="">有用</a> </span>
     <span class="comment-info"> <a href="https://www.douban.com/people/darlingtudai/" class="">刘小黛</a> <span>看过</span> <span class="allstar20 rating" title="较差"></span> <span class="comment-time " title="2017-10-31 00:16:20"> 2017-10-31 </span> </span> </h3>
     <p class=""> 本来没有高期待，但还是比预想的差。大段样板戏，情感莫名，想讲大时代，涉及敏感又浅尝辄止，不深刻不入心，这点比潘金莲好不了多少。我是钢炮，但我还是不能钢，可你们要以为我钢着。尤其一段文工团解散戏，涂脂抹粉的脸和僵硬的表情，夸张矫情的歌唱，最难看的群戏。是不是唱《血染的风采》更合适？ </p>
     </div>
     * @param page
     */
    @Override
    public void process(Page page) {
        try {
            System.out.println("-------------------等2秒钟----------------------");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.addTargetRequest("https://movie.douban.com/subject/26862829/comments?start="+(i+=20)+"&limit=20&sort=new_score&status=P&percent_type=");

        page.putField("comment",page.getHtml().css("div.comment p","text").all());
        page.putField("userName",page.getHtml().css("span.comment-info a","text").all());
        page.putField("evaluateLevel",page.getHtml().css("span.rating","class").all()
            .stream().map(s->s.substring(7,9)).collect(Collectors.toList()));
        page.putField("evaluateDate",page.getHtml().css("span.comment-time","title").all());
        page.putField("approvalCount",page.getHtml().css("span.votes","text").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
            Spider.create(new DoubanPageProcessor()).addUrl("https://movie.douban.com/subject/26862829/comments?start="+0+"&limit=20&sort=new_score&status=P&percent_type=")
                    .addPipeline(new JsonFilePipeline("webmagic\\"))
                    .addPipeline(new DoubanPipeline())
                    .thread(60).run();

    }
}
