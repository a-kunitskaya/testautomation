package com.kunitskaya.business.operations.pf;

import com.kunitskaya.business.objects.Email;
import com.kunitskaya.pages.pf.ComposeEmailPopup;
import com.kunitskaya.pages.pf.MailDetailsPage;
import com.kunitskaya.pages.pf.MailListingPage;
import com.kunitskaya.pages.pf.MailPage;
import com.kunitskaya.test.Folders;

public class EmailOperations {
    /**
     * Creates email
     *
     * @param email -  email instance
     */
    public static void createEmail(Email email) {
        new MailPage().clickComposeButton()
                      .fillInToField(email.getReceiver())
                      .fillInSubjectField(email.getSubject())
                      .fillInBodyField(email.getBody());
    }

    /**
     * Saves the opened email a draft
     */
    public static void saveEmailAsDraft() {
        new ComposeEmailPopup().clickCloseButton();
    }

    /**
     * Opens a draft email
     *
     * @param email - draft email
     */
    public static void openDraft(Email email) {
        new MailPage().clickDraftsFolderLink()
                      .openDraftWithSubject(email.getSubject());
    }

    /**
     * Sends the opened email
     */
    public static void sendEmail() {
        new ComposeEmailPopup().clickSendButton();
    }

    /**
     * Receives email details
     *
     * @param email  - email instance
     * @param folder - the folder of the email
     * @return email details
     */
    public static String getMailContent(Email email, Folders folder) {
        String emailContent;
        switch (folder) {
            case DRAFT:
                emailContent = new ComposeEmailPopup().getTo() + new ComposeEmailPopup().getBody(email.getSubject());
                break;
            case SENT:
                MailDetailsPage mailDetails = new MailDetailsPage();
                new MailListingPage().openEmailWithSubject(email.getSubject());

                if (email.getSubject().isEmpty() || email.getBody().isEmpty()) {
                    emailContent = mailDetails.getSubject() + mailDetails.getTo();
                } else {
                    emailContent = mailDetails.getSubject() + mailDetails.getTo() + mailDetails.getBody();
                }
                break;
            default:
                throw new IllegalArgumentException("No such folder is found for " + folder.name());
        }
        return emailContent;
    }

    /**
     * Sends empty email
     *
     * @param to - email receiver
     */
    public static void sendEmptyEmail(String to) {
        new MailPage().clickComposeButton()
                      .fillInToField(to)
                      .sendEmailWithHotKeys();
    }
}
