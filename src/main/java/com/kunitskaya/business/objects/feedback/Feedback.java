package com.kunitskaya.business.objects.feedback;

import java.util.Objects;

public class Feedback {
    String header;
    String input;
    String inputFieldPlaceholder;

    boolean isIncludeScreenshotChecked;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInputFieldPlaceholder() {
        return inputFieldPlaceholder;
    }

    public void setInputFieldPlaceholder(String inputFieldPlaceholder) {
        this.inputFieldPlaceholder = inputFieldPlaceholder;
    }

    public boolean isIncludeScreenshotChecked() {
        return isIncludeScreenshotChecked;
    }

    public void setIncludeScreenshotChecked(boolean includeScreenshotChecked) {
        isIncludeScreenshotChecked = includeScreenshotChecked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return isIncludeScreenshotChecked == feedback.isIncludeScreenshotChecked &&
                Objects.equals(header, feedback.header) &&
                Objects.equals(input, feedback.input) &&
                Objects.equals(inputFieldPlaceholder, feedback.inputFieldPlaceholder);
    }

    @Override
    public int hashCode() {

        return Objects.hash(header, input, inputFieldPlaceholder, isIncludeScreenshotChecked);
    }
}
