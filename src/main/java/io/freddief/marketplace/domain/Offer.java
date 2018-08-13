package io.freddief.marketplace.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

import static io.freddief.marketplace.domain.Side.OFFER;

@Getter
@Builder
@AllArgsConstructor
public class Offer implements LimitOrder {

    private static final Side SIDE = OFFER;

    private final String id;
    private final String itemId;
    private final Long quantity;
    private final BigDecimal price;
    private final String userId;
    private final Instant timestamp;

    @Override
    public Side getSide() {
        return SIDE;
    }

}
