package me.robertlit.recommendationservice.config;

import me.robertlit.recommendationservice.strategy.RandomRecommendationStrategy;
import me.robertlit.recommendationservice.strategy.RecommendationStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RecommendationServiceConfiguration {

    @Value("http://${API_GATEWAY_URI}")
    private String apiGatewayUrl;

    @Bean
    public RecommendationStrategy recommendationStrategy() {
        return new RandomRecommendationStrategy();
    }

    @Bean
    public WebClient apiGatewayClient() {
        return WebClient.create(apiGatewayUrl);
    }
}
