package me.robertlit.recommendationservice.controller;

import me.robertlit.recommendationservice.model.Content;
import me.robertlit.recommendationservice.model.User;
import me.robertlit.recommendationservice.service.APIGatewayService;
import me.robertlit.recommendationservice.strategy.RecommendationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class RecommendationController {

    private final APIGatewayService apiGatewayService;
    private final RecommendationStrategy recommendationStrategy;

    @Autowired
    public RecommendationController(APIGatewayService apiGatewayService, RecommendationStrategy recommendationStrategy) {
        this.apiGatewayService = apiGatewayService;
        this.recommendationStrategy = recommendationStrategy;
    }

    @GetMapping("/recommendations/{userId}")
    public Mono<List<UUID>> getRecommendationsForUser(@PathVariable UUID userId, @RequestParam(defaultValue = "5") int length) {
        Mono<User> userMono = apiGatewayService.getUser(userId);
        Mono<List<Content>> allContentMono = apiGatewayService.getAllContent();

        return Mono.zip(userMono, allContentMono)
                .flatMap(tuple -> {
                    User user = tuple.getT1();
                    List<Content> allContent = tuple.getT2();

                    List<Content> recommendedContent = recommendationStrategy.recommendContentForUser(user, length, allContent);
                    return Mono.just(recommendedContent.stream()
                            .map(Content::getId)
                            .collect(Collectors.toList())
                    );
                });
    }
}
