package io.freddief.marketplace.validator;

import com.google.common.collect.Lists;
import io.freddief.marketplace.domain.LimitOrder;
import io.freddief.marketplace.exception.ValidationException;
import io.freddief.marketplace.exception.ValidationFailure;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class LimitOrderValidator {

    @Autowired
    public LimitOrderValidator() {
    }

    public void validate(LimitOrder limitOrder) {

        List<ValidationFailure> failures = Lists.newArrayList();

        if (StringUtils.isBlank(limitOrder.getId())) {
            failures.add(new ValidationFailure("'id' must be present"));
        }
        if (StringUtils.isBlank(limitOrder.getItemId())) {
            failures.add(new ValidationFailure("'itemId' must be present"));
        }
        if (limitOrder.getQuantity() == null || limitOrder.getQuantity() <= 0) {
            failures.add(new ValidationFailure("'quantity' must be present and cannot be less than or equal to zero"));
        }
        if (limitOrder.getPrice() == null || limitOrder.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            failures.add(new ValidationFailure("'price' must be present and cannot be less than or equal to zero"));
        }
        if (StringUtils.isBlank(limitOrder.getUserId())) {
            failures.add(new ValidationFailure("'userId' must be present"));
        }
        if (limitOrder.getTimestamp() == null) {
            failures.add(new ValidationFailure("'timestamp' must be present"));
        }

        if (!failures.isEmpty()) {
            throw new ValidationException(failures);
        }

    }

}
