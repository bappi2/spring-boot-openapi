package com.mmefta.springbootopenapi.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.s3.InMemoryBufferingS3OutputStreamProvider;
import io.awspring.cloud.s3.Jackson2JsonS3ObjectConverter;
import io.awspring.cloud.s3.S3Template;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class StorageConfig {
    @Value("${spring.cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String accessSecret;
    @Value("${spring.cloud.aws.region.static}")
    private String region;

    private final String bucketName = "your-s3-bucket-name";

    @Bean
    public S3Client s3Client() {
        AwsCredentialsProvider credentialsProvider =
                StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, accessSecret));
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(credentialsProvider)
                .build();
    }

    @Bean
    public S3Template s3Template(S3Client s3Client) {
        return new S3Template(s3Client(), new InMemoryBufferingS3OutputStreamProvider(s3Client(),
                null), new Jackson2JsonS3ObjectConverter(new ObjectMapper()),
                S3Presigner.create());
    }
}
