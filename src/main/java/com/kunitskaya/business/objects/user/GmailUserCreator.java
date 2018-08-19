package com.kunitskaya.business.objects.user;

import com.kunitskaya.test.TestDataProvider;

public class GmailUserCreator implements UserCreator {
    @Override
    public User createUser() {
        return TestDataProvider.getGmailUser();
    }
}
