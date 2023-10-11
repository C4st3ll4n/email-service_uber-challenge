package br.com.phenrique.emailservice.presenter;

public record SendEmailRequestDTO(String to, String subject, String payload) {
}
