package io.freddief.marketplace.dto;

import io.freddief.marketplace.domain.Bid;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateBidTest {

    @Test
    public void toDomain() {

        CreateBid createBid = new CreateBid(
            "itemId",
            12L,
            BigDecimal.valueOf(123.12),
            "userId"
        );

        Bid returned = createBid.toDomain();

        assertThat(returned.getId()).isNotNull();
        assertThat(returned.getItemId()).isEqualTo("itemId");
        assertThat(returned.getQuantity()).isEqualTo(12L);
        assertThat(returned.getPricePerUnit()).isEqualTo(BigDecimal.valueOf(123.12));
        assertThat(returned.getUserId()).isEqualTo("userId");
        assertThat(returned.getTimestamp()).isBetween(Instant.now().minusSeconds(3), Instant.now().plusSeconds(3));

    }

}