package ckollmeier.de.webstarter;

import lombok.With;

@With
public record Message(
        String name,
        String message,
        String id
) {
}
