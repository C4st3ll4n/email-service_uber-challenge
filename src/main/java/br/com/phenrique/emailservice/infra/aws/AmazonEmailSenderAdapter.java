package br.com.phenrique.emailservice.infra.aws;

import br.com.phenrique.emailservice.data.infra.EmailSenderGateway;
import br.com.phenrique.emailservice.data.infra.EmailServiceException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AmazonEmailSender")

public class AmazonEmailSenderAdapter implements EmailSenderGateway {

    private final AmazonSimpleEmailService client;

    @Autowired
    public AmazonEmailSenderAdapter(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.client = amazonSimpleEmailService;
    }

    @Override
    public void send(String to, String subject, String payload) {
        final Destination destination = new Destination(List.of(to));
        final Message message = new Message(new Content(subject), new Body(new Content(payload)));
        SendEmailRequest request = new SendEmailRequest("p13dr0h@gmail.com", destination, message);

        try {
            System.out.println("Sending email with amazon");

            client.sendEmail(request);
        }catch (AmazonServiceException exception){
            //System.out.println("Failure:"+exception.toString());
            throw new EmailServiceException("Fail to send email with Amazon SES", exception);
        }
    }
}
