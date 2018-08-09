package com.kunitskaya.business.operations;

import com.kunitskaya.business.objects.Email;
import com.kunitskaya.pages.pf.ComposeEmailPopup;
import com.kunitskaya.pages.pf.MailDetailsPage;
import com.kunitskaya.pages.pf.MailListingPage;
import com.kunitskaya.pages.pf.MailPage;

public class EmailOperations {

    public static void createEmail(Email email) {
        new MailPage().clickComposeButton()
                      .fillInToField(email.getReceiver())
                      .fillInSubjectField(email.getSubject())
                      .fillInBodyField(email.getBody());
    }

    public static MailPage saveEmailAsDraft(ComposeEmailPopup emailContent) {
        return emailContent.clickCloseButton();
    }


    public static ComposeEmailPopup openDraft(Email email) {
        return new MailPage()
                .clickDraftsFolderLink()
                .openDraftWithSubject(email.getSubject());
    }

    public static MailListingPage sendEmail() {
        return new ComposeEmailPopup().clickSendButton();
    }

    public static String getMailContent(Email email) {
        new MailListingPage().openEmailWithSubject(email.getSubject());

        if (email.getReceiver().isEmpty() || email.getSubject().isEmpty() || email.getBody().isEmpty()) {
            return new MailDetailsPage().getMailContent();
        } else {
            return new MailDetailsPage().getMailFullContent();
        }
    }

    public static void sendEmptyEmail(String to) {
        new MailPage().clickComposeButton()
                      .fillInToField(to)
                      .sendEmailWithHotKeys();
    }
}
