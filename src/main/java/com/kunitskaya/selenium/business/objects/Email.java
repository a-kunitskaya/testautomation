package com.kunitskaya.selenium.business.objects;

import java.util.Objects;

public class Email {
    private String subject;
    private String receiver;
    private String body;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(subject, email.subject) &&
                Objects.equals(receiver, email.receiver) &&
                Objects.equals(body, email.body);
    }

    @Override
    public int hashCode() {

        return Objects.hash(subject, receiver, body);
    }

    @Override
    public String toString() {
        return "Email{" +
                "subject='" + subject + '\'' +
                ", receiver='" + receiver + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
