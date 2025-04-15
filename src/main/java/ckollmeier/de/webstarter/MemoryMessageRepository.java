package ckollmeier.de.webstarter;

import lombok.NonNull;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile("memory")
public class MemoryMessageRepository implements MessageRepository {
    /**
     * The messages, indexed by their id.
     */
    private final Map<String, Message> messages = new HashMap<>();
    /**
     * the last id of a message.
     */
    private String lastId = "-1";

    @Override
    public Optional<Message> find(final @NonNull String id) {
        return Optional.ofNullable(messages.get(id));
    }

    @Override
    public List<Message> findAll() {
        return new ArrayList<>(messages.values());
    }

    @Override
    public Message create(final @NonNull String name, final @NonNull String message) {
        lastId = Long.toString(Long.parseLong(lastId) + 1);
        Message msg = new Message(name, message, lastId);
        put(lastId, msg);
        return msg;
    }

    @Override
    public Message put(final @NonNull String id, final @NonNull Message message) {
        return messages.put(id, message);
    }

    @Override
    public void delete(final @NonNull String id) {
        if (messages.remove(id) == null) {
            throw new MessageNotFoundException("Message not found");
        }
    }
}
