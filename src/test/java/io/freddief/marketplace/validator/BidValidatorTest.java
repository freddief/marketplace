package io.freddief.marketplace.validator;

import io.freddief.marketplace.domain.Bid;
import io.freddief.marketplace.exception.ValidationException;
import io.freddief.marketplace.exception.ValidationFailure;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class BidValidatorTest {

    @InjectMocks
    private BidValidator bidValidator;

    @Test
    public void validate_valid() {

        Bid bid = Bid.builder()
            .id(UUID.randomUUID().toString())
            .itemId("itemId")
            .userId("userId")
            .pricePerUnit(BigDecimal.TEN)
            .quantity(12L)
            .timestamp(Instant.now())
            .build();

        bidValidator.validate(bid);

    }

    @Test(expected = ValidationException.class)
    public void validate_whenNullFields_throwException() {

        Bid bid = Bid.builder().build();

        try {
            bidValidator.validate(bid);
        } catch (ValidationException ve) {

            assertThat(ve.getFailures()).hasSize(6);

            assertThat(ve.getFailures()).containsAll(
                Lists.newArrayList(
                    new ValidationFailure("'id' must be present"),
                    new ValidationFailure("'itemId' must be present"),
                    new ValidationFailure("'quantity' must be present and cannot be less than or equal to zero"),
                    new ValidationFailure("'pricePerUnit' must be present and cannot be less than or equal to zero"),
                    new ValidationFailure("'userId' must be present"),
                    new ValidationFailure("'timestamp' must be present")
                ));

            throw ve;
        }

    }

    @Test(expected = ValidationException.class)
    public void validate_whenQuantityZero_throwException() {


        Bid bid = Bid.builder()
            .id(UUID.randomUUID().toString())
            .itemId("itemId")
            .userId("userId")
            .pricePerUnit(BigDecimal.TEN)
            .quantity(0L)
            .timestamp(Instant.now())
            .build();


        try {
            bidValidator.validate(bid);
        } catch (ValidationException ve) {

            assertThat(ve.getFailures()).hasSize(1);

            assertThat(ve.getFailures()).contains(
                new ValidationFailure("'quantity' must be present and cannot be less than or equal to zero")
            );

            throw ve;
        }

    }

    @Test(expected = ValidationException.class)
    public void validate_whenQuantityLessThanZero_throwException() {


        Bid bid = Bid.builder()
            .id(UUID.randomUUID().toString())
            .itemId("itemId")
            .userId("userId")
            .pricePerUnit(BigDecimal.TEN)
            .quantity(-2L)
            .timestamp(Instant.now())
            .build();


        try {
            bidValidator.validate(bid);
        } catch (ValidationException ve) {

            assertThat(ve.getFailures()).hasSize(1);

            assertThat(ve.getFailures()).contains(
                new ValidationFailure("'quantity' must be present and cannot be less than or equal to zero")
            );

            throw ve;
        }

    }

    @Test(expected = ValidationException.class)
    public void validate_whenPricePerUnitZero_throwException() {


        Bid bid = Bid.builder()
            .id(UUID.randomUUID().toString())
            .itemId("itemId")
            .userId("userId")
            .pricePerUnit(BigDecimal.ZERO)
            .quantity(20L)
            .timestamp(Instant.now())
            .build();


        try {
            bidValidator.validate(bid);
        } catch (ValidationException ve) {

            assertThat(ve.getFailures()).hasSize(1);

            assertThat(ve.getFailures()).contains(
                new ValidationFailure("'pricePerUnit' must be present and cannot be less than or equal to zero")
            );

            throw ve;
        }

    }

    @Test(expected = ValidationException.class)
    public void validate_whenPricePerUnitLessThanZero_throwException() {


        Bid bid = Bid.builder()
            .id(UUID.randomUUID().toString())
            .itemId("itemId")
            .userId("userId")
            .pricePerUnit(BigDecimal.valueOf(-3))
            .quantity(20L)
            .timestamp(Instant.now())
            .build();


        try {
            bidValidator.validate(bid);
        } catch (ValidationException ve) {

            assertThat(ve.getFailures()).hasSize(1);

            assertThat(ve.getFailures()).contains(
                new ValidationFailure("'pricePerUnit' must be present and cannot be less than or equal to zero")
            );

            throw ve;
        }

    }

}