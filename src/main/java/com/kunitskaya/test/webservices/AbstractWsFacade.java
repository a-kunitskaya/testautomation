package com.kunitskaya.test.webservices;

import com.kunitskaya.base.ConfigProvider;

public class AbstractWsFacade {
    protected ConfigProvider configProvider = ConfigProvider.getInstance();
    protected String baseUrl = configProvider.getBaseWsTestUrl();
}
