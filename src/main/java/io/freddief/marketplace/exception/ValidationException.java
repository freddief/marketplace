package io.freddief.marketplace.exception;


import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {

    private final List<ErrorMessage> failures;

    public ValidationException(ErrorMessage failure) {
        super();
        this.failures = Lists.newArrayList(failure);
    }

    public ValidationException(ErrorMessage... failures) {
        super();
        this.failures = Lists.newArrayList(failures);
    }

}
