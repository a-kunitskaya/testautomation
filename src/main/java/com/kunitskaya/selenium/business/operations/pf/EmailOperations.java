package com.kunitskaya.selenium.business.operations.pf;

import com.kunitskaya.selenium.business.objects.Email;
import com.kunitskaya.selenium.pages.pf.ComposeEmailPopup;
import com.kunitskaya.selenium.pages.pf.MailDetailsPage;
import com.kunitskaya.selenium.pages.pf.MailPage;
import com.kunitskaya.test.selenium.Folders;

public class EmailOperations {

    /**
     * Creates and sends email
     *
     * @param email - expected email instance
     */
    public static void createAndSendEmail(Email email) {
        createEmail(email);
        new ComposeEmailPopup().sendEmailWithHotKeys();
    }

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
    public static void sendEmail(Folders folder) {
        new ComposeEmailPopup().clickSendButton();
    }

    /**
     * Sends the opened email
     */
    public static void sendEmail() {
        new ComposeEmailPopup().sendEmailWithHotKeys();
    }


    /**
     * Receives email details
     *
     * @param folder - the folder of the email
     * @return email object with actual details
     */
    public static Email getActualEmail(Folders folder) {
        Email actualEmail = new Email();
        switch (folder) {
            case DRAFT:
                ComposeEmailPopup emailPopup = new ComposeEmailPopup();
                actualEmail.setReceiver(emailPopup.getTo());
                actualEmail.setSubject(emailPopup.getSubject());
                actualEmail.setBody(emailPopup.getBody(actualEmail.getSubject()));
                break;
            case SENT:
                MailDetailsPage mailDetails = new MailDetailsPage();
                actualEmail.setSubject(mailDetails.getSubject());
                actualEmail.setBody(mailDetails.getBody());
                actualEmail.setReceiver(mailDetails.getTo());
                break;
            default:
                throw new IllegalArgumentException("No such folder is found for " + folder.name());

        }
        return actualEmail;
    }
}
