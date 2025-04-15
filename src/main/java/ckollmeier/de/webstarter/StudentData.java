package ckollmeier.de.webstarter;

public record StudentData(
        String firstName,
        String lastName,
        String emailAddress,
        Gender gender
) {
}
