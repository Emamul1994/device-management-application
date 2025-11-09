package com.otp.validation.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AWSConfig {

    @Value("${aws.region}")
    private String region;

    public SnsClient snsClient() {
        return SnsClient.builder()
                .region(Region.of(region))
                .build();
    }
}
