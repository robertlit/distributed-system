package me.robertlit.recommendationservice.strategy;

import me.robertlit.recommendationservice.model.Content;
import me.robertlit.recommendationservice.model.User;

import java.util.List;

public interface RecommendationStrategy {

    List<Content> recommendContentForUser(User user, int length, List<Content> content);
}
