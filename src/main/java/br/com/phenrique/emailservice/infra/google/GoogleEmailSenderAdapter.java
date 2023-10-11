package br.com.phenrique.emailservice.infra.google;

import br.com.phenrique.emailservice.data.infra.EmailSenderGateway;
import br.com.phenrique.emailservice.data.infra.EmailServiceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.stereotype.Service;

@Service("GoogleEmailSender")
public class GoogleEmailSenderAdapter implements EmailSenderGateway {
    @Override
    public void send(String to, String subject, String payload) {
        try {
            System.out.println("Sending email with google");

            //Send email
            throw new RuntimeException("Something went wrong");
        }catch (Exception e){
            System.out.println("Failure:"+e.toString());
            throw new EmailServiceException("Fail to send email with Google", e);
        }
    }
}
