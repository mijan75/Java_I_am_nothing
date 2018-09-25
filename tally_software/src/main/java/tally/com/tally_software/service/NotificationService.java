package tally.com.tally_software.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tally.com.tally_software.model.User;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Date;

@Service
public class NotificationService {
    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    };


    @Async
    public void sendNotification(User user) throws MailException {
        System.out.println("Sleeping now...");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sending email...");
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("nazmul8842@gmail.com");
        mail.setSubject("Username and Password Sending mail.");
        mail.setText("Dear " + user.getName() + "\n\n" +
                "This email contains your accounting application account credentials: \n\n" +
                "Your Username : " + user.getUsername() + "\n" +
                "Your Password :  " + user.getPassword() + "\n\n" +
                "We recommend to change your password in order to improve your privacy. You can change your password once you login with these credentials and use 'Edit Profile' option. Please do not reply to this email, use 'Contact Support' inside our app to contact us."
        );



        javaMailSender.send(mail);
        System.out.println("Email Sent!");
    }

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }
}

