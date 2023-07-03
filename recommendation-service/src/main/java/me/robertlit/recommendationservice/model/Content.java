package me.robertlit.recommendationservice.model;

import java.util.UUID;

public class Content {

    private final UUID id;
    private final String name;

    public Content() {
        this(null, null);
    }

    public Content(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
