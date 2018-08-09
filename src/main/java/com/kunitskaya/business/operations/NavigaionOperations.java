package com.kunitskaya.business.operations;

import com.kunitskaya.pages.pf.MailPage;

public class NavigaionOperations {

    public static void goToSentMailFolder(){
        MailPage mailPage = new MailPage();
        mailPage.clickSentMailLink();
    }
}
