package com.kunitskaya.test;

import com.kunitskaya.business.objects.email.Email;
import com.kunitskaya.business.objects.user.User;

import static com.kunitskaya.base.utils.DateTimeUtil.SUBJECT_TIMESTAMP_PATTERN;
import static com.kunitskaya.base.utils.DateTimeUtil.getFormattedTimestamp;

public class TestDataProvider {
    private static final String USERNAME = "aktestautomation@gmail.com";
    private static final String PASSWORD = "123456Ak";

    private static final String SUBJECT = "Hello Selenium ";
    private static final String BODY = "This email is sent with a test script";
    private static final String RECEIVER = "cfiftest@gmail.com";

    public static User getGmailUser() {
        return new User.Builder()
                .withUsername(USERNAME)
                .withPassword(PASSWORD)
                .build();
    }

    public static Email getDefaultEmail() {
        Email email = new Email();
        email.setSubject(SUBJECT.concat(getFormattedTimestamp(SUBJECT_TIMESTAMP_PATTERN)));
        email.setBody(BODY);
        email.setReceiver(RECEIVER);
        return email;
    }

    public static Email getEmailWithoutBody() {
        Email email = new Email();
        email.setSubject(SUBJECT.concat(getFormattedTimestamp(SUBJECT_TIMESTAMP_PATTERN)));
        email.setReceiver(RECEIVER);
        return email;
    }
}
