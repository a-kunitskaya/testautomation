package com.kunitskaya.test;

import com.kunitskaya.business.objects.user.User;
import com.kunitskaya.business.objects.email.Email;

import static com.kunitskaya.base.utils.DateTimeUtil.SUBJECT_TIMESTAMP_PATTERN;
import static com.kunitskaya.base.utils.DateTimeUtil.getFormattedTimestamp;

public class TestDataProvider {
    private static final String USERNAME = "aktestautomation@gmail.com";
    private static final String PASSWORD = "123456Ak";

    private static final String SUBJECT = "Hello Selenium ";
    private static final String BODY = "This email is sent with a test script";
    private static final String RECEIVER = "cfiftest@gmail.com";

    public static User getUser() {
        return new User.Builder()
                .withUsername(USERNAME)
                .withPassword(PASSWORD)
                .build();
    }

    public static Email getDefaultGmailEmail() {
        return new Email.Builder()
                .withSubject(SUBJECT.concat(getFormattedTimestamp(SUBJECT_TIMESTAMP_PATTERN)))
                .withBody(BODY)
                .withReceiver(RECEIVER)
                .build();
    }
}
