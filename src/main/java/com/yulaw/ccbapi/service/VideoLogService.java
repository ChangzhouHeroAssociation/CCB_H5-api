package com.yulaw.ccbapi.service;

import org.springframework.transaction.annotation.Transactional;

public interface VideoLogService {
    @Transactional
    public void task();

    @Transactional
    public void update();
}
