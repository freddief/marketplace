package io.freddief.marketplace.domain;

import java.math.BigDecimal;
import java.time.Instant;

public interface LimitOrder {

    String getId();

    String getItemId();

    String getUserId();

    Long getQuantity();

    BigDecimal getPrice();

    Instant getTimestamp();

    Side getSide();

}
