package com.myproject.journalApp.Schedular;

import com.myproject.journalApp.Repository.UserRepositoryIml;
import com.myproject.journalApp.Services.EmailService;
import com.myproject.journalApp.cache.AppCache;
import com.myproject.journalApp.entity.JournalEntry;
import com.myproject.journalApp.enums.Sentiment;
import com.myproject.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserSchedule {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepositoryIml userRepositoryIml;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUserAndSendSaMail() {
        List<User> users = userRepositoryIml.GetUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }

            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if (mostFrequentSentiment != null) {
                emailService.sendMail(user.getEmail(),"Sentiment For Last 7 Days",mostFrequentSentiment.toString());
            }

        }

    }
    @Scheduled(cron = "0 0/10 0 ? * * ")
    public void clearcache() {
        appCache.init();
    }

}
