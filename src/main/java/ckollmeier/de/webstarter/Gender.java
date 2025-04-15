package ckollmeier.de.webstarter;

/**
 * Represents the gender of a person or entity.
 */
public enum Gender {
    /**
     * Represents the male gender.
     */
    MALE,

    /**
     * Represents the female gender.
     */
    FEMALE,

    /**
     * Represents genders other than male or female,
     * or cases where gender is not specified or applicable.
     */
    OTHER;

    /**
     * @return salutation based on the gender
     */
    public String salutation() {
        return switch (this) {
            case MALE -> "Mr.";
            case FEMALE -> "Ms.";
            case OTHER -> "Mx.";
        };
    }
}