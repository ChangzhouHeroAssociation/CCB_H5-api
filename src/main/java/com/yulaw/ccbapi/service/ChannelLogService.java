package com.yulaw.ccbapi.service;

import org.springframework.transaction.annotation.Transactional;

public interface ChannelLogService {
    @Transactional
    void task();
}
