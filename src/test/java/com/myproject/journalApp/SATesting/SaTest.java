package com.myproject.journalApp.SATesting;

import com.myproject.journalApp.Schedular.UserSchedule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest
public class SaTest {

    @Autowired
    private UserSchedule userSchedule;

    @Test
    public void testsa() {
        userSchedule.fetchUserAndSendSaMail();
    }
}
