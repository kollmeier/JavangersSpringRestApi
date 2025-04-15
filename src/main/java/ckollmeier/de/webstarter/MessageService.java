package ckollmeier.de.webstarter;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for managing {@link Message} entities.
 * Provides business logic for creating, retrieving, and deleting messages.
 */
@Service
@RequiredArgsConstructor
public class MessageService {
    /**
     * Message repository for data access.
     */
    private final MessageRepository messageRepository;

    /**
     * Creates a new message with the given name and content.
     *
     * @param name    The name associated with the message (e.g., sender). Cannot be null or empty.
     * @param message The content of the message. Cannot be null or empty.
     * @return The newly created {@link Message} entity.
     * @throws IllegalArgumentException if name or message is invalid (e.g., null or empty, depending on repository implementation).
     */
    public Message createMessage(final @NonNull String name, final @NonNull String message) {
        return messageRepository.create(name, message);
    }

    /**
     * Retrieves a message by its unique identifier.
     *
     * @param id The unique ID of the message to retrieve. Cannot be null.
     * @return The found {@link Message} entity.
     * @throws MessageNotFoundException if no message is found with the given ID.
     */
    public Message getMessage(final @NonNull String id) {
        return messageRepository.find(id).orElseThrow(() -> new MessageNotFoundException("Message not found with ID: " + id));
    }

    /**
     * Retrieves all messages stored in the repository.
     *
     * @return A {@link List} containing all {@link Message} entities. Returns an empty list if no messages exist.
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /**
     * Deletes a message by its unique identifier.
     * If no message exists with the given ID, the operation completes silently.
     *
     * @param id The unique ID of the message to delete. Cannot be null.
     */
    public void deleteMessage(final @NonNull String id) {
        messageRepository.delete(id);
    }
}