package com.kunitskaya.test;

import com.kunitskaya.business.objects.Email;
import com.kunitskaya.business.objects.Feedback;
import com.kunitskaya.business.objects.user.User;
import org.apache.commons.lang3.StringUtils;

import static com.kunitskaya.base.utils.DateTimeUtil.SUBJECT_TIMESTAMP_PATTERN;
import static com.kunitskaya.base.utils.DateTimeUtil.getFormattedTimestamp;

public class TestDataProvider {
    private static final String USERNAME = "aktestautomation@gmail.com";
    private static final String PASSWORD = "123456Ak";
    private static final String INVALID_PASSWORD = "pwd";

    private static final String SUBJECT = "Hello Selenium ";
    private static final String BODY = "This email is sent with a test script";
    private static final String RECEIVER = "cfiftest@gmail.com";

    private static final String FEEDBACK_TEXT = "Feedback";


    public static User getGmailUser() {
        return new User.Builder()
                .withUsername(USERNAME)
                .withPassword(PASSWORD)
                .build();
    }

    public static User getInvalidPasswordGmailUser() {
        return new User.Builder()
                .withUsername(USERNAME)
                .withPassword(INVALID_PASSWORD)
                .build();
    }

    public static Email getDefaultEmail() {
        Email email = new Email();
        email.setSubject(SUBJECT.concat(getFormattedTimestamp(SUBJECT_TIMESTAMP_PATTERN)));
        email.setBody(BODY);
        email.setReceiver(RECEIVER);
        return email;
    }

    public static Email getEmailWithoutSubjectAndBody() {
        Email email = new Email();
        email.setReceiver(RECEIVER);
        email.setSubject(StringUtils.EMPTY);
        email.setBody(StringUtils.EMPTY);
        return email;
    }

    public static Feedback getDefaultFeedback() {
        Feedback feedback = new Feedback();
        feedback.setFeedbackText(FEEDBACK_TEXT);
        feedback.setWithScreenshot(true);
        return feedback;
    }
}
