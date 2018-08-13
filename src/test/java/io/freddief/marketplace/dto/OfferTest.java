package io.freddief.marketplace.dto;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class OfferTest {

    @Test
    public void fromDomain() {

        Instant now = Instant.now();

        io.freddief.marketplace.domain.Offer domain = new io.freddief.marketplace.domain.Offer(
            "id",
            "itemId",
            12L,
            BigDecimal.valueOf(123.12),
            "userId",
            now
        );

        Offer returned = Offer.fromDomain(domain);

        assertThat(returned.getId()).isNotNull();
        assertThat(returned.getItemId()).isEqualTo("itemId");
        assertThat(returned.getQuantity()).isEqualTo(12L);
        assertThat(returned.getPricePerUnit()).isEqualTo(BigDecimal.valueOf(123.12));
        assertThat(returned.getUserId()).isEqualTo("userId");
        assertThat(returned.getTimestamp()).isEqualTo(now);

    }
}