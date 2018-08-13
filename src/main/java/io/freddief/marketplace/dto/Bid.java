package io.freddief.marketplace.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class Bid {

    private final String id;
    private final String itemId;
    private final Long quantity;
    private final BigDecimal pricePerUnit;
    private final String userId;
    private final Instant timestamp;

    @JsonCreator
    public Bid(@JsonProperty("id") String id,
               @JsonProperty("itemId") String itemId,
               @JsonProperty("quantity") Long quantity,
               @JsonProperty("pricePerUnit") BigDecimal pricePerUnit,
               @JsonProperty("userId") String userId,
               @JsonProperty("timestamp") Instant timestamp) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public static Bid fromDomain(io.freddief.marketplace.domain.Bid domain) {
        return new Bid(
            domain.getId(),
            domain.getItemId(),
            domain.getQuantity(),
            domain.getPricePerUnit(),
            domain.getUserId(),
            domain.getTimestamp()
        );
    }

}
