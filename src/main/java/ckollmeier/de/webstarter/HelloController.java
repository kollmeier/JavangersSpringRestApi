package ckollmeier.de.webstarter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/hello")
public class HelloController {
    /**
     * @param toWho Who to greet
     * @return the greeting
     */
    @GetMapping("/{toWho}")
    public String sayHello(final @PathVariable String toWho) {
        return "Hello " + toWho;
    }

    /**
     * @param studentData The data of the student
     * @return the greeting
     */
    @PostMapping()
    public String postStudent(final @RequestBody StudentData studentData) {
        return String.format("Hello %s %s %s, sending an email to %s", studentData.gender().salutation(), studentData.firstName(), studentData.lastName(), studentData.emailAddress());
    }
}
