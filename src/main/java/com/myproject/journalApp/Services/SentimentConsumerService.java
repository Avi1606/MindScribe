package com.myproject.journalApp.Services;


import com.myproject.journalApp.model.SentimentData;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SentimentConsumerService {

    @Autowired
    private EmailService emailService;

//    @KafkaListener(topics = "weekly-sentiment", groupId = "weekly-sentiment-group")
//    public void comsume(SentimentData sentimentData) {
//        sendEmail(sentimentData);
//    }

    private void sendEmail(SentimentData sentimentData) {
        emailService.sendMail(sentimentData.getEmail(),"Sentiment for previous week", sentimentData.getSentiment());
    }
}
