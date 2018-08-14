package io.freddief.marketplace.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ErrorMessage {

    private final String message;

    @JsonCreator
    public ErrorMessage(@JsonProperty("message") String message) {
        this.message = message;
    }

}
