package io.freddief.marketplace.acceptancetest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.freddief.marketplace.dto.Bid;
import io.freddief.marketplace.dto.CreateBid;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;

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
                .post(formatUrl("/bids"))
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

    @Test
    public void create_validationFailure_400() throws JsonProcessingException {

        CreateBid createOffer = new CreateBid(
            null,
            0L,
            BigDecimal.ZERO,
            null
        );

        given()
            .contentType("application/json")
            .body(objectMapper.writeValueAsString(createOffer))
        .when()
            .post(formatUrl("/bids"))
        .then()
            .statusCode(400)
            .body("[0].message", is("'itemId' must be present"))
            .body("[1].message", is("'quantity' must be present and cannot be less than or equal to zero"))
            .body("[2].message", is("'price' must be present and cannot be less than or equal to zero"))
            .body("[3].message", is("'userId' must be present"));

    }

}
