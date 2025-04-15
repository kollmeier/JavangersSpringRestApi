package ckollmeier.de.webstarter;

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
    public Optional<Message> find(final String id) {
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
    public Message create(final String name, final String message) {
        lastId = Long.toString(Long.parseLong(lastId) + 1);
        Message msg = new Message(name, message, lastId);
        put(lastId, msg);
        return msg;
    }

    /**
     * @param id id of the message
     * @param message the new message
     */
    public void put(final String id, final Message message) {
        messages.put(id, message);
    }

    /**
     * @param id id of the message
     */
    public void delete(final String id) {
        messages.remove(id);
    }
}
