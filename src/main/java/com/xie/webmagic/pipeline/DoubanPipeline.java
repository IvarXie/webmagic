package com.xie.webmagic.pipeline;


import com.xie.webmagic.model.DoubanComment;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @Creation Date : 2018-03-30 16:28
 * Email is xie.wenbo@jyall.com
 * Copyright is 金色家园网络科技有限公司
 */
@Component
public class DoubanPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<String> userNames = resultItems.get("userName");
        List<String> comments = resultItems.get("comment");
        List<String> evaluateLevels = resultItems.get("evaluateLevel");
        List<String> evaluateDates = resultItems.get("evaluateDate");
        List<String> approvalCount = resultItems.get("approvalCount");
        for(int i = 0;i<userNames.size();i++){
            DoubanComment doubanComment = new DoubanComment();
            doubanComment.setMovieName("芳华");
            doubanComment.setEvaluateLevel(evaluateLevels.get(i));
            doubanComment.setEvaluateDate(evaluateDates.get(i));
            doubanComment.setComment(comments.get(i));
            doubanComment.setApprovalCount(Integer.getInteger(approvalCount.get(i)));
            doubanComment.setUserName(userNames.get(i));
        }


        System.out.println(resultItems.toString());
    }
}
