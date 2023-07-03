package me.robertlit.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ServiceConfiguration {

    @Value("${USER_SERVICE_URI}")
    private String userServiceUri;

    @Value("${USER_SERVICE_PATH}")
    private String userServicePath;

    @Value("${USER_SERVICE_TARGET_PATH}")
    private String userServiceTargetPath;

    @Value("${CONTENT_SERVICE_URI}")
    private String contentServiceUri;

    @Value("${CONTENT_SERVICE_PATH}")
    private String contentServicePath;

    @Value("${CONTENT_SERVICE_TARGET_PATH}")
    private String contentServiceTargetPath;

    @Value("${RECOMMENDATION_SERVICE_URI}")
    private String recommendationServiceUri;

    @Value("${RECOMMENDATION_SERVICE_PATH}")
    private String recommendationServicePath;

    @Value("${RECOMMENDATION_SERVICE_TARGET_PATH}")
    private String recommendationServiceTargetPath;

    @Bean
    public List<Service> serviceList() {
        return List.of(
                new Service("user-service", userServiceUri, userServicePath, userServiceTargetPath),
                new Service("content-service", contentServiceUri, contentServicePath, contentServiceTargetPath),
                new Service("recommendation-service", recommendationServiceUri, recommendationServicePath, recommendationServiceTargetPath)
        );
    }
}
