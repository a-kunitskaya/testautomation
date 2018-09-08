package com.kunitskaya.base.test;

import com.kunitskaya.base.utils.files.CsvFileReader;
import com.kunitskaya.selenium.business.objects.Email;
import com.kunitskaya.selenium.business.objects.Feedback;
import com.kunitskaya.selenium.business.objects.user.User;
import com.kunitskaya.webservices.models.Gps;
import com.kunitskaya.webservices.models.ToDo;
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

    private static final String TODO_ID = "20";
    private static final String TODO_USER_ID = "20";
    private static final String TODO_TITLE = "A new created todo";
    private static final String TODO_COMPLETED = "false";

    private static final double GPS_LONGITUDE = 40.9026;
    private static final double GPS_LATITUDE = 174.8865;

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

    public static String getLoginErrorMessage(Languages language) {
        return new CsvFileReader().getTranslationForElement(language, TranslationsElements.LOGIN_ERROR);
    }

    public static ToDo getDefaultTodo() {
        ToDo toDo = new ToDo();
        toDo.setId(TODO_ID);
        toDo.setUserId(TODO_USER_ID);
        toDo.setTitle(TODO_TITLE);
        toDo.setCompleted(TODO_COMPLETED);
        return toDo;
    }

    public static Gps getDefaultGps() {
        return new Gps(GPS_LONGITUDE, GPS_LATITUDE);
    }
}
