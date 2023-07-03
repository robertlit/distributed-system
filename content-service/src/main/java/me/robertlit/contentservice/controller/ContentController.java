package me.robertlit.contentservice.controller;

import me.robertlit.contentservice.exception.ContentNotFoundException;
import me.robertlit.contentservice.model.Content;
import me.robertlit.contentservice.repository.ContentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ContentController {

    private final ContentRepository contentRepository;

    public ContentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @GetMapping("/content/{id}")
    public Content getContent(@PathVariable UUID id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException(id));
    }

    @GetMapping("/content")
    public List<Content> getAllContent() {
        return contentRepository.findAll();
    }

    @PostMapping("/content")
    public Content saveContent(@RequestBody Content content) {
        return contentRepository.save(content);
    }
}
