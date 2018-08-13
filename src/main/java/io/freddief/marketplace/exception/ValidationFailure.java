package io.freddief.marketplace.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ValidationFailure {

    private final String reason;

    @JsonCreator
    public ValidationFailure(@JsonProperty("reason") String reason) {
        this.reason = reason;
    }

}
