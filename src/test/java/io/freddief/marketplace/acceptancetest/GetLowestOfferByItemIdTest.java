package io.freddief.marketplace.acceptancetest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.freddief.marketplace.dto.CreateOffer;
import io.freddief.marketplace.dto.Offer;
import org.junit.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

public class GetLowestOfferByItemIdTest extends BaseAcceptanceTest {

    @Test
    public void getLowestPricedOfferByItemId() throws JsonProcessingException {

        CreateOffer createOffer1 = new CreateOffer(
            "item123",
            12L,
            BigDecimal.valueOf(1.5),
            "user1"
        );

        CreateOffer createOffer2 = new CreateOffer(
            "item123",
            12L,
            BigDecimal.valueOf(1.1),
            "user1"
        );

        CreateOffer createOffer3 = new CreateOffer(
            "item123",
            12L,
            BigDecimal.valueOf(1.8),
            "user2"
        );

        CreateOffer createOffer4 = new CreateOffer(
            "item1234566",
            12L,
            BigDecimal.valueOf(0.3),
            "user2"
        );

        Offer offer1 = createOffer(createOffer1);

        Offer offer2 = createOffer(createOffer2);

        Offer offer3 = createOffer(createOffer3);

        Offer offer4 = createOffer(createOffer4);

        // When

        Offer offer =
            when()
                .get(formatUrl("/offers/items/item123/lowest"))
            .then()
                .statusCode(200)
                .extract()
                .as(Offer.class);

        assertThat(offer).isEqualTo(offer2);

    }

    private Offer createOffer(CreateOffer createOffer1) throws JsonProcessingException {
        return given()
                .contentType("application/json")
                .body(objectMapper.writeValueAsString(createOffer1))
            .when()
                .post(formatUrl("/offers"))
            .then()
                .statusCode(200)
                .extract()
                .as(Offer.class);
    }


}
