package ckollmeier.de.webstarter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.NonNull;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

@Repository
@Profile("file")
public class FileMessageRepository implements MessageRepository {
    private static Path PATH = Path.of("messages.json");
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FileMessageRepository() {
        if (!PATH.toFile().exists()) {
            try {
                PATH.toFile().createNewFile();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            init();
        }
    }

    /**
     * The messages, indexed by their id.
     */
    private final Map<String, Message> messages = new HashMap<>();
    /**
     * the last id of a message.
     */
    private String lastId = "-1";

    private void flush() {
        try {
            objectMapper.writeValue(PATH.toFile(), messages);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void init() {
        try {
            messages.putAll(objectMapper.readValue(
                    PATH.toFile(),
                    new TypeReference<Map<String, Message>>() {
                    }
            ));
            lastId = messages.keySet().stream().max(Comparator.comparingLong(Long::parseLong)).orElse("-1");
        } catch (MismatchedInputException mi) {
            messages.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
        flush();
        return msg;
    }

    @Override
    public Message put(final @NonNull String id, final @NonNull Message message) {
        messages.put(id, message);
        flush();
        return  message;
    }

    @Override
    public void delete(final @NonNull String id) {
        if (messages.remove(id) == null) {
            throw new MessageNotFoundException("Message not found");
        }
        flush();
    }
}
