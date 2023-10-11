package br.com.phenrique.emailservice.data.usecase;

import br.com.phenrique.emailservice.data.infra.EmailServiceException;
import br.com.phenrique.emailservice.domain.usecase.EmailSenderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EmailSenderUseCaseFallback implements EmailSenderUseCase {

    private final EmailSenderUseCaseImpl amazon;
    private final EmailSenderUseCaseImpl google;

    @Autowired

    public EmailSenderUseCaseFallback(EmailSenderUseCaseImpl google, EmailSenderUseCaseImpl amazon) {
        this.amazon = amazon;
        this.google = google;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        try {
            google.sendEmail(to,subject,body);
        }catch (Exception e){
            System.out.println(e.getMessage());
            try {
                amazon.sendEmail(to,subject,body);
            }catch (Exception exception){
                System.out.println(exception.getMessage());
                throw new EmailServiceException("Fail to send email for the available providers");
            }
        }
    }
}
