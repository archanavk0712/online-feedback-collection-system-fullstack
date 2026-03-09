package com.dyashin.onlinefeedbackcollectionsystem.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.dyashin.onlinefeedbackcollectionsystem.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendFeedbackReviewedMail(String toEmail, String userName, String productName) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your Feedback Has Been Reviewed");

        message.setText(
            "Hello " + userName + ",\n\n" +
            "Thank you for your feedback on the product \"" + productName + "\".\n\n" +
            "We would like to inform you that your feedback has been successfully reviewed by our admin.\n\n" +
            "We appreciate your time and effort in helping us improve.\n\n" +
            "Best Regards,\n" +
            "Online Feedback Management System"
        );

        mailSender.send(message);
    }
}
