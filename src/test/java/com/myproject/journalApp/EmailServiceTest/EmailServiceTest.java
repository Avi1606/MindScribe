package com.myproject.journalApp.EmailServiceTest;

import com.myproject.journalApp.Services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testmailservice() {
        emailService.sendMail("allrounder16062004@gmail.com",
                "testing java mail sender",
                "this is auto generated mail");
    }
}