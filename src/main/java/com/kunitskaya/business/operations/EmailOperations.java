package com.kunitskaya.business.operations;

import com.kunitskaya.business.objects.Email;
import com.kunitskaya.pages.ComposeEmailPopup;
import com.kunitskaya.pages.MailListingPage;
import com.kunitskaya.pages.MailPage;

public class EmailOperations {

    public static ComposeEmailPopup createEmail(Email email) {
        return new MailPage()
                .clickComposeButton()
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

    public static String getSentMailFullContent(Email email) {
        return new MailPage()
                .clickSentMailLink()
                .openEmailWithSubject(email.getSubject())
                .getMailFullContent();
    }

    public static String getSentMailPartialContent(Email email) {
        return new MailPage()
                .clickSentMailLink()
                .openEmailWithSubject(email.getSubject())
                .getMailContent();
    }
}
