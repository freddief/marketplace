package io.freddief.marketplace.validator;

import com.google.common.collect.Lists;
import io.freddief.marketplace.domain.Bid;
import io.freddief.marketplace.exception.ValidationException;
import io.freddief.marketplace.exception.ValidationFailure;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class BidValidator {

    @Autowired
    public BidValidator() {
    }

    public void validate(Bid bid) {

        List<ValidationFailure> failures = Lists.newArrayList();

        if (StringUtils.isBlank(bid.getId())) {
            failures.add(new ValidationFailure("'id' must be present"));
        }
        if (StringUtils.isBlank(bid.getItemId())) {
            failures.add(new ValidationFailure("'itemId' must be present"));
        }
        if (bid.getQuantity() == null || bid.getQuantity() <= 0) {
            failures.add(new ValidationFailure("'quantity' must be present and cannot be less than or equal to zero"));
        }
        if (bid.getPricePerUnit() == null || bid.getPricePerUnit().compareTo(BigDecimal.ZERO) <= 0) {
            failures.add(new ValidationFailure("'pricePerUnit' must be present and cannot be less than or equal to zero"));
        }
        if (StringUtils.isBlank(bid.getUserId())) {
            failures.add(new ValidationFailure("'userId' must be present"));
        }
        if (bid.getTimestamp() == null) {
            failures.add(new ValidationFailure("'timestamp' must be present"));
        }

        if (!failures.isEmpty()) {
            throw new ValidationException(failures);
        }

    }

}
