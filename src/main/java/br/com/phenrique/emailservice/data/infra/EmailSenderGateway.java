package br.com.phenrique.emailservice.data.infra;

public interface EmailSenderGateway {
    void send(String to, String subject, String payload);
}
