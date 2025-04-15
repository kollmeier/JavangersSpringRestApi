package ckollmeier.de.webstarter;

import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MessageRepository {
    /**
     * The messages, indexed by their id.
     */
    private final Map<String, Message> messages = new HashMap<>();
    /**
     * the last id of a message.
     */
    private String lastId = "-1";

    /**
     * @param id id of the message
     * @return the message with the given id as an optional
     */
    public Optional<Message> find(final @NonNull String id) {
        return Optional.ofNullable(messages.get(id));
    }

    /**
     * @return all messages
     */
    public List<Message> findAll() {
        return new ArrayList<>(messages.values());
    }

    /**
     * @param name to whom the message is addressed
     * @param message the message
     * @return the created message
     */
    public Message create(final @NonNull String name, final @NonNull String message) {
        lastId = Long.toString(Long.parseLong(lastId) + 1);
        Message msg = new Message(name, message, lastId);
        return put(lastId, msg);
    }

    /**
     * @param id id of the message
     * @param message the new message
     */
    public Message put(final @NonNull String id, final @NonNull Message message) {
        return messages.put(id, message);
    }

    /**
     * @param id id of the message
     */
    public void delete(final @NonNull String id) {
        if (messages.remove(id) == null) {
            throw new MessageNotFoundException("Message not found");
        }
    }
}
