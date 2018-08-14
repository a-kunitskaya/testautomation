package com.kunitskaya.business.objects.email;

import com.kunitskaya.test.TestDataProvider;

public class GmailEmailCreator implements EmailCreator {
    @Override
    public Email createEmail() {
        return TestDataProvider.getDefaultGmailEmail();
    }
}
