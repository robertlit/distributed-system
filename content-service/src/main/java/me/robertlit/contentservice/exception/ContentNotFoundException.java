package me.robertlit.contentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such content")
public class ContentNotFoundException extends RuntimeException {

    public ContentNotFoundException(UUID id) {
        super("Could not find content " + id);
    }
}
