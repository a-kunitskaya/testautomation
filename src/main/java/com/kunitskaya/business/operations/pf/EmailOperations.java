package com.kunitskaya.business.operations.pf;

import com.kunitskaya.business.objects.email.Email;
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
     * Sends the opened draft email
     */
    public static void sendDraftEmail() {
        new ComposeEmailPopup().clickSendButton();
    }

    /**
     * Receives email details
     *
     * @param email  - email instance
     * @param folder - the folder of the email
     * @return email details
     */
    public static Email getActualEmail(Email email, Folders folder) {
        Email actualEmail = new Email();
        switch (folder) {
            case DRAFT:
                ComposeEmailPopup emailPopup = new ComposeEmailPopup();
                actualEmail.setReceiver(emailPopup.getTo());
                actualEmail.setBody(emailPopup.getBody(email.getSubject()));
                actualEmail.setSubject(emailPopup.getSubject());
                break;
            case SENT:
                MailDetailsPage mailDetails = new MailDetailsPage();
                new MailListingPage().openEmailWithSubject(email.getSubject());
                if (email.getBody() == null) {
                    actualEmail.setSubject(mailDetails.getSubject());
                    actualEmail.setReceiver(mailDetails.getTo());
                } else {
                    actualEmail.setSubject(mailDetails.getSubject());
                    actualEmail.setBody(mailDetails.getBody());
                    actualEmail.setReceiver(mailDetails.getTo());
                }
                break;
            default:
                throw new IllegalArgumentException("No such folder is found for " + folder.name());
        }
        return actualEmail;
    }

    /**
     * Creates and sends email with filled in "to"
     *
     * @param to - email receiver
     */
    public static void sendEmail(String to) {
        new MailPage().clickComposeButton()
                      .fillInToField(to)
                      .sendEmailWithHotKeys();

    }
}
