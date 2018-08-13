package io.freddief.marketplace.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class Bid {

    private final String id;
    private final String itemId;
    private final Long quantity;
    private final BigDecimal pricePerUnit;
    private final String userId;
    private final Instant timestamp;

}
