package com.application.upskilling.service;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    
    private String code;

    public void sendVerificationCode(String toEmail) {
        String verificationCode = generateCode();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your Verification Code");
        message.setText("Your code is: " + verificationCode);
        message.setFrom("your-email@gmail.com");

        mailSender.send(message);
        System.out.println("Verification code sent to: " + toEmail);
    }
    
    public Boolean verifyCode(String code) {
        System.out.println("Verifying: received=" + code + ", expected=" + this.code);
        return this.code != null && this.code.equals(code);
    }

    private String generateCode() {
        Random random = new Random();
        code = "" + (100000 + random.nextInt(900000)); 
        return String.valueOf(code);
    }
}

