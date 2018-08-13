package io.freddief.marketplace.acceptancetest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import io.freddief.marketplace.dto.Bid;
import io.freddief.marketplace.dto.CreateBid;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

public class GetBidsByUserIdTest extends BaseAcceptanceTest {

    @Test
    public void getBidsByUserId() throws JsonProcessingException {

        CreateBid createBid1 = new CreateBid(
            "item123",
            12L,
            BigDecimal.valueOf(1.5),
            "user1"
        );

        CreateBid createBid2 = new CreateBid(
            "item123",
            12L,
            BigDecimal.valueOf(1.5),
            "user1"
        );

        CreateBid createBid3 = new CreateBid(
            "item123",
            12L,
            BigDecimal.valueOf(1.5),
            "user2"
        );

        Bid bid1 = createBid(createBid1);

        Bid bid2 = createBid(createBid2);

        Bid bid3 = createBid(createBid3);


        // When
        List<Bid> bids = Lists.newArrayList(
            when()
                .get(formatUrl("/bids/users/user1"))
            .then()
                .statusCode(200)
                .extract()
                .as(Bid[].class));

        assertThat(bids).hasSize(2);
        assertThat(bids).contains(bid1);
        assertThat(bids).contains(bid2);

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
