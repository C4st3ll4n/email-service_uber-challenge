package br.com.phenrique.emailservice.domain.usecase;

public interface EmailSenderUseCase {
    void sendEmail(String to, String subject, String body);
}
