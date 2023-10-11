package br.com.phenrique.emailservice.presenter;

import br.com.phenrique.emailservice.data.usecase.EmailSenderUseCaseFallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class SendEmailController {

    private final EmailSenderUseCaseFallback emailSenderUseCase;

    @Autowired
    public SendEmailController(EmailSenderUseCaseFallback emailSenderUseCase) {
        this.emailSenderUseCase = emailSenderUseCase;
    }

    @PostMapping()
    public ResponseEntity<String> sendEmail(@RequestBody SendEmailRequestDTO body) {
        System.out.println(emailSenderUseCase.getClass().getName());
        try {
            this.emailSenderUseCase.sendEmail(body.to(), body.subject(), body.payload());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong, try again later");
        }

        return ResponseEntity.ok().body("Email sent successfully");
    }
}
