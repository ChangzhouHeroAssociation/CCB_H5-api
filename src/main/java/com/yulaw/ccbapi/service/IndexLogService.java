package com.yulaw.ccbapi.service;

import org.springframework.transaction.annotation.Transactional;

public interface IndexLogService {

    void addIndexViewCount();
    @Transactional
    public void task();
}
