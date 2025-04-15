package ckollmeier.de.webstarter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/messages")
@RequiredArgsConstructor
public class MessageController {
    /**
     * the message repository.
     */
    @Autowired
    private final MessageService messageService;

    /**
     * @return Response
     */
    @GetMapping("/")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    /**
     * @param id id of the message
     * @return Response
     */
    @GetMapping("/{id}")
    public Message getMessage(final @PathVariable String id) {
        return messageService.getMessage(id);
    }

    /**
     * @param message the message to create
     * @return Response
     */
    @PostMapping("/")
    public ResponseEntity<Message> postMessage(final @RequestBody Message message) {
        Message msg = messageService.createMessage(message.name(), message.message());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(msg.id())
                .toUri();

        return ResponseEntity.created(uri)
                .body(msg);
    }

    /**
     * @param id id of the message to delete
     * @return Response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(final @PathVariable String id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

}
