package me.robertlit.recommendationservice.strategy;

import me.robertlit.recommendationservice.model.Content;
import me.robertlit.recommendationservice.model.User;

import java.util.Collections;
import java.util.List;

public class RandomRecommendationStrategy implements RecommendationStrategy {

    @Override
    public List<Content> recommendContentForUser(User user, int length, List<Content> content) {
        Collections.shuffle(content);
        return content.subList(0, Math.min(length, content.size()));
    }
}
