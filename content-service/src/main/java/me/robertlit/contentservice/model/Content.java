package me.robertlit.contentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Content {

    @Id
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
