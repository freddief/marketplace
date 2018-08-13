package io.freddief.marketplace.acceptancetest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.freddief.marketplace.dto.CreateOffer;
import io.freddief.marketplace.dto.Offer;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;

public class CreateOfferTest extends BaseAcceptanceTest {

    @Test
    public void create_success() throws JsonProcessingException {

        CreateOffer createOffer = new CreateOffer(
            "item123",
            12L,
            BigDecimal.valueOf(1.5),
            "user123"
        );

        Offer offer =
            given()
                .contentType("application/json")
                .body(objectMapper.writeValueAsString(createOffer))
            .when()
                .post(formatUrl("/offer"))
            .then()
                .statusCode(200)
                .extract()
                .as(Offer.class);

        assertThat(offer.getId()).isNotNull();
        assertThat(offer.getItemId()).isEqualTo("item123");
        assertThat(offer.getQuantity()).isEqualTo(12L);
        assertThat(offer.getPricePerUnit()).isEqualTo(BigDecimal.valueOf(1.5));
        assertThat(offer.getUserId()).isEqualTo("user123");
        assertThat(offer.getTimestamp()).isBetween(Instant.now().minusSeconds(5), Instant.now().plusSeconds(5));

    }

    @Test
    public void create_validationFailure_400() throws JsonProcessingException {

        CreateOffer createOffer = new CreateOffer(
            null,
            0L,
            BigDecimal.ZERO,
            null
        );

        given()
            .contentType("application/json")
            .body(objectMapper.writeValueAsString(createOffer))
        .when()
            .post(formatUrl("/offer"))
        .then()
            .statusCode(400)
            .body("[0].reason", is("'itemId' must be present"))
            .body("[1].reason", is("'quantity' must be present and cannot be less than or equal to zero"))
            .body("[2].reason", is("'price' must be present and cannot be less than or equal to zero"))
            .body("[3].reason", is("'userId' must be present"));


    }

}
