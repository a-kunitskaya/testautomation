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
    public static Email getActualEmail(Email email, Folders folder) {
        Email actualEmail = new Email();
        switch (folder) {
            case DRAFT:
                ComposeEmailPopup emailPopup = new ComposeEmailPopup();
                // emailContent = new ComposeEmailPopup().getTo() + new ComposeEmailPopup().getBody(email.getSubject());
                actualEmail.setReceiver(emailPopup.getTo());
                actualEmail.setBody(emailPopup.getBody(email.getSubject()));
                actualEmail.setSubject(emailPopup.getSubject());
                break;
            case SENT:
                MailDetailsPage mailDetails = new MailDetailsPage();
                new MailListingPage().openEmailWithSubject(email.getSubject());
                if (email.getBody() == null) {
                    // emailContent = mailDetails.getSubject() + mailDetails.getTo();
                    actualEmail.setSubject(mailDetails.getSubject());
                    actualEmail.setReceiver(mailDetails.getTo());
                } else {
                    // emailContent = mailDetails.getSubject() + mailDetails.getTo() + mailDetails.getBody();
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
     * Sends empty email
     *
     * @param to - email receiver
     */
    public static void sendEmptyEmail(String to) {
        try {
            new MailPage().clickComposeButton()
                          .fillInToField(to)
                          .sendEmailWithHotKeys();
        } catch (Exception e) {
            System.out.println("Exection in EmailOperations");


        }
    }
}
