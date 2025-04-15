package ckollmeier.de.webstarter;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {
    /**
     * @param id id of the message
     * @return the message with the given id as an optional
     */
    Optional<Message> find(@NonNull String id);

    /**
     * @return all messages
     */
    List<Message> findAll();

    /**
     * @param name    to whom the message is addressed
     * @param message the message
     * @return the created message
     */
    Message create(@NonNull String name, @NonNull String message);

    /**
     * @param id      id of the message
     * @param message the new message
     */
    Message put(@NonNull String id, @NonNull Message message);

    /**
     * @param id id of the message
     */
    void delete(@NonNull String id);
}
