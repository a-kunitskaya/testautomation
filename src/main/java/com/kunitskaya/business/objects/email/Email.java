package com.kunitskaya.business.objects.email;

public class Email {
    private String subject;
    private String receiver;
    private String body;

    public String getSubject() {
        return subject;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getBody() {
        return body;
    }

    public Email(Builder builder) {
        this.subject = builder.subject;
        this.receiver = builder.receiver;
        this.body = builder.body;
    }

    public static class Builder {
        private String subject;
        private String receiver;
        private String body;

        public Builder withSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder withReceiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        public Builder withBody(String body) {
            this.body = body;
            return this;
        }

        public Email build() {
            return new Email(this);
        }
    }
}
