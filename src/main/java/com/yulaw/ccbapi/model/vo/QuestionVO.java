package com.yulaw.ccbapi.model.vo;

import java.io.Serializable;
import java.util.List;

public class QuestionVO implements Serializable {

    private Long id;

    private String title;

    private String[] optionList;

    private Integer category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getOptionList() {
        return optionList;
    }

    public void setOptionList(String[] optionList) {
        this.optionList = optionList;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}



