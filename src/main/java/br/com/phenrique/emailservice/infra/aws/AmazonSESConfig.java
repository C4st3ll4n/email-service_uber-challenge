package br.com.phenrique.emailservice.infra.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration

public class AmazonSESConfig {

    @Autowired
    Environment env;

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {

        String region = env.getProperty("aws-region");
        String accessKey = env.getProperty("aws-accessKeyId");
        String secretKey = env.getProperty("aws-secretKey");

        AmazonSimpleEmailServiceClientBuilder builder = AmazonSimpleEmailServiceClientBuilder.standard();
        builder.setRegion(region);
        builder.setCredentials(new AWSCredentialsProvider() {
            @Override
            public AWSCredentials getCredentials() {
                return new AWSCredentials() {
                    @Override
                    public String getAWSAccessKeyId() {
                        return accessKey;
                    }

                    @Override
                    public String getAWSSecretKey() {
                        return secretKey;
                    }
                };
            }

            @Override
            public void refresh() {
            }
        });
        return builder.build();
    }
}
