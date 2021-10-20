package com.yulaw.ccbapi.model.request;

import javax.validation.constraints.NotNull;

public class AddAnswerReq {
    @NotNull(message = "questionId不能为null")
    private Long questionId;
    @NotNull(message = "result不能为null")
    private String result;
    @NotNull(message = "channelId不能为null")
    private Long channelId;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "AddAnswerReq{" +
                "questionId=" + questionId +
                ", result='" + result + '\'' +
                ", channelId=" + channelId +
                '}';
    }
}
