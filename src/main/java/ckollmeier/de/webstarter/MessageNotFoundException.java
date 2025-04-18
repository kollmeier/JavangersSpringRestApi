package ckollmeier.de.webstarter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(final String message) {
        super(message);
    }
}
