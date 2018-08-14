package io.freddief.marketplace.acceptancetest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.freddief.marketplace.dto.Bid;
import io.freddief.marketplace.dto.CreateBid;
import org.junit.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

public class GetHighestBidByItemIdTest extends BaseAcceptanceTest {

    @Test
    public void getHighestPricedBidByItemId() throws JsonProcessingException {
        CreateBid createBid1 = new CreateBid(
            "item123",
            12L,
            BigDecimal.valueOf(1.5),
            "user1"
        );

        CreateBid createBid2 = new CreateBid(
            "item123",
            12L,
            BigDecimal.valueOf(1.8),
            "user1"
        );

        CreateBid createBid3 = new CreateBid(
            "item123",
            12L,
            BigDecimal.valueOf(1.1),
            "user2"
        );

        CreateBid createBid4 = new CreateBid(
            "item123asdasds",
            12L,
            BigDecimal.valueOf(31),
            "user2"
        );

        Bid bid1 = createBid(createBid1);

        Bid bid2 = createBid(createBid2);

        Bid bid3 = createBid(createBid3);

        Bid bid4 = createBid(createBid4);


        // When
        Bid bid =
            when()
                .get(formatUrl("/bids/items/item123/highest"))
            .then()
                .statusCode(200)
                .extract()
                .as(Bid.class);

        assertThat(bid).isEqualTo(bid2);

    }

    private Bid createBid(CreateBid createBid1) throws JsonProcessingException {
        return given()
            .contentType("application/json")
            .body(objectMapper.writeValueAsString(createBid1))
        .when()
            .post(formatUrl("/bids"))
        .then()
            .statusCode(200)
            .extract()
            .as(Bid.class);
    }


}
