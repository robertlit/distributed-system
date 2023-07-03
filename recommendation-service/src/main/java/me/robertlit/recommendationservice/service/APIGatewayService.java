package me.robertlit.recommendationservice.service;

import me.robertlit.recommendationservice.model.Content;
import me.robertlit.recommendationservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
public class APIGatewayService {

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private final WebClient apiGatewayClient;

    @Autowired
    public APIGatewayService(WebClient apiGatewayClient) {
        this.apiGatewayClient = apiGatewayClient;
    }

    public Mono<User> getUser(UUID id) {
        return apiGatewayClient.get()
                .uri("/users/" + id)
                .retrieve()
                .bodyToMono(User.class);
    }

    public Mono<List<Content>> getAllContent() {
        return apiGatewayClient.get()
                .uri("/content")
                .retrieve()
                .bodyToFlux(Content.class)
                .collectList();
    }
}
