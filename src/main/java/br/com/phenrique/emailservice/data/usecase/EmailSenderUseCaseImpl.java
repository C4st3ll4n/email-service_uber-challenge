package br.com.phenrique.emailservice.data.usecase;

import br.com.phenrique.emailservice.data.infra.EmailSenderGateway;
import br.com.phenrique.emailservice.domain.usecase.EmailSenderUseCase;
import org.springframework.stereotype.Service;

public class EmailSenderUseCaseImpl implements EmailSenderUseCase {

    final EmailSenderGateway esg;

    public EmailSenderUseCaseImpl(EmailSenderGateway esg) {
        this.esg = esg;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        this.esg.send(to,subject,body);
    }
}
