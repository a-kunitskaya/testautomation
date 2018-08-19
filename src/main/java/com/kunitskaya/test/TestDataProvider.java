package com.kunitskaya.test;

import com.kunitskaya.business.objects.email.Email;
import com.kunitskaya.business.objects.feedback.Feedback;
import com.kunitskaya.business.objects.user.User;

import static com.kunitskaya.base.utils.DateTimeUtil.SUBJECT_TIMESTAMP_PATTERN;
import static com.kunitskaya.base.utils.DateTimeUtil.getFormattedTimestamp;

public class TestDataProvider {
    private static final String USERNAME = "aktestautomation@gmail.com";
    private static final String PASSWORD = "123456Ak";
    private static final String INVALID_PASSWORD = "pwd";

    private static final String SUBJECT = "Hello Selenium ";
    private static final String BODY = "This email is sent with a test script";
    private static final String RECEIVER = "cfiftest@gmail.com";

    private static final String FEEDBACK_POPUP_HEADER = "Send feedback";
    private static final String FEEDBACK_POPUP_INPUT_PLACEHOLDER = "Describe your issue or share your ideas";


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

    public static Email getEmailWithoutBody() {
        Email email = new Email();
        email.setSubject(SUBJECT.concat(getFormattedTimestamp(SUBJECT_TIMESTAMP_PATTERN)));
        email.setReceiver(RECEIVER);
        return email;
    }

    public static Feedback getDefaultFeedback() {
        Feedback feedback = new Feedback();
        feedback.setHeader(FEEDBACK_POPUP_HEADER);
        feedback.setInputFieldPlaceholder(FEEDBACK_POPUP_INPUT_PLACEHOLDER);
        feedback.setInput("Feedback");
        feedback.setIncludeScreenshotChecked(true);
        return feedback;
    }
}
