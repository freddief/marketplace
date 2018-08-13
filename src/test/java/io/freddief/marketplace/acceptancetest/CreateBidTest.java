package io.freddief.marketplace.acceptancetest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.freddief.marketplace.dto.Bid;
import io.freddief.marketplace.dto.CreateBid;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateBidTest extends BaseAcceptanceTest {

    @Test
    public void create_success() throws JsonProcessingException {

        CreateBid createBid = new CreateBid(
            "item123",
            12L,
            BigDecimal.valueOf(1.5),
            "user123"
        );

        Bid bid =
            given()
                .contentType("application/json")
                .body(objectMapper.writeValueAsString(createBid))
            .when()
                .post(formatUrl("/bid"))
            .then()
                .statusCode(200)
                .extract()
                .as(Bid.class);

        assertThat(bid.getId()).isNotNull();
        assertThat(bid.getItemId()).isEqualTo("item123");
        assertThat(bid.getQuantity()).isEqualTo(12L);
        assertThat(bid.getPricePerUnit()).isEqualTo(BigDecimal.valueOf(1.5));
        assertThat(bid.getUserId()).isEqualTo("user123");
        assertThat(bid.getTimestamp()).isBetween(Instant.now().minusSeconds(5), Instant.now().plusSeconds(5));
    }

}
