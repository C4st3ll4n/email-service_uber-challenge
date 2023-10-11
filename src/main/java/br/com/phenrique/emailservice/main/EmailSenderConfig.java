package br.com.phenrique.emailservice.main;

import br.com.phenrique.emailservice.data.usecase.EmailSenderUseCaseFallback;
import br.com.phenrique.emailservice.data.usecase.EmailSenderUseCaseImpl;
import br.com.phenrique.emailservice.domain.usecase.EmailSenderUseCase;
import br.com.phenrique.emailservice.infra.aws.AmazonEmailSenderAdapter;
import br.com.phenrique.emailservice.infra.google.GoogleEmailSenderAdapter;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailSenderConfig {

    @Bean
    public EmailSenderUseCase emailSenderUseCase(AmazonSimpleEmailService amazonSimpleEmailService){
        EmailSenderUseCaseImpl amazon = new EmailSenderUseCaseImpl(new AmazonEmailSenderAdapter(amazonSimpleEmailService));
        EmailSenderUseCaseImpl google = new EmailSenderUseCaseImpl(new GoogleEmailSenderAdapter());

        return new EmailSenderUseCaseFallback(google, amazon);
    }
}
