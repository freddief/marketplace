package io.freddief.marketplace.acceptancetest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import io.freddief.marketplace.dto.Offer;
import io.freddief.marketplace.dto.CreateOffer;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

public class GetOffersByUserIdTest extends BaseAcceptanceTest {

    @Test
    public void getOffersByUserId() throws JsonProcessingException {

        CreateOffer createOffer1 = new CreateOffer(
            "item123",
            12L,
            BigDecimal.valueOf(1.5),
            "user1"
        );

        CreateOffer createOffer2 = new CreateOffer(
            "item123",
            12L,
            BigDecimal.valueOf(1.5),
            "user1"
        );

        CreateOffer createOffer3 = new CreateOffer(
            "item123",
            12L,
            BigDecimal.valueOf(1.5),
            "user2"
        );

        Offer offer1 = createOffer(createOffer1);

        Offer offer2 = createOffer(createOffer2);

        Offer offer3 = createOffer(createOffer3);

        // When
        List<Offer> offers = Lists.newArrayList(
            when()
                .get(formatUrl("/offers/users/user1"))
            .then()
                .statusCode(200)
                .extract()
                .as(Offer[].class));

        assertThat(offers).hasSize(2);
        assertThat(offers).contains(offer1);
        assertThat(offers).contains(offer2);
        
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
