package io.freddief.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.freddief.marketplace.domain.Bid;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
public class CreateBid {

    private final String itemId;
    private final Long quantity;
    private final BigDecimal pricePerUnit;
    private final String userId;

    @JsonCreator
    public CreateBid(@JsonProperty("itemId") String itemId,
                     @JsonProperty("quantity") Long quantity,
                     @JsonProperty("pricePerUnit") BigDecimal pricePerUnit,
                     @JsonProperty("userId") String userId) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.userId = userId;
    }

    public Bid toDomain() {
        return new Bid(
            UUID.randomUUID().toString(),
            itemId,
            quantity,
            pricePerUnit,
            userId,
            Instant.now()
        );
    }
}