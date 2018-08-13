package io.freddief.marketplace.dto;

import io.freddief.marketplace.domain.Offer;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateOfferTest {

    @Test
    public void toDomain() {

        CreateOffer createOffer = new CreateOffer(
            "itemId",
            12L,
            BigDecimal.valueOf(123.12),
            "userId"
        );

        Offer returned = createOffer.toDomain();

        assertThat(returned.getId()).isNotNull();
        assertThat(returned.getItemId()).isEqualTo("itemId");
        assertThat(returned.getQuantity()).isEqualTo(12L);
        assertThat(returned.getPrice()).isEqualTo(BigDecimal.valueOf(123.12));
        assertThat(returned.getUserId()).isEqualTo("userId");
        assertThat(returned.getTimestamp()).isBetween(Instant.now().minusSeconds(3), Instant.now().plusSeconds(3));

    }

}