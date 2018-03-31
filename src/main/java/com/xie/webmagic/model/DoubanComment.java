package com.xie.webmagic.model;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @Creation Date : 2018-03-30 15:49
 * Email is xie.wenbo@jyall.com
 * Copyright is 金色家园网络科技有限公司
 */
public class DoubanComment  {
    /**
     * 电影名
     */
    private String movieName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 评价
     */
    private String comment;
    /**
     * 评价分数
     */
    private Integer evaluateLevel;
    /**
     * 评价日期
     */
    private String evaluateDate;
    /**
     * 赞同数
     */
    private Integer approvalCount;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(Integer evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    public String getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public Integer getApprovalCount() {
        return approvalCount;
    }

    public void setApprovalCount(Integer approvalCount) {
        this.approvalCount = approvalCount;
    }
}
