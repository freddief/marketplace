package io.freddief.marketplace.validator;

import io.freddief.marketplace.domain.Bid;
import io.freddief.marketplace.exception.ValidationException;
import io.freddief.marketplace.exception.ErrorMessage;
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
public class LimitOrderValidatorTest {

    @InjectMocks
    private LimitOrderValidator limitOrderValidator;

    @Test
    public void validate_valid() {

        Bid bid = Bid.builder()
            .id(UUID.randomUUID().toString())
            .itemId("itemId")
            .userId("userId")
            .price(BigDecimal.TEN)
            .quantity(12L)
            .timestamp(Instant.now())
            .build();

        limitOrderValidator.validate(bid);

    }

    @Test(expected = ValidationException.class)
    public void validate_whenNullFields_throwException() {

        Bid bid = Bid.builder().build();

        try {
            limitOrderValidator.validate(bid);
        } catch (ValidationException ve) {

            assertThat(ve.getFailures()).hasSize(6);

            assertThat(ve.getFailures()).containsAll(
                Lists.newArrayList(
                    new ErrorMessage("'id' must be present"),
                    new ErrorMessage("'itemId' must be present"),
                    new ErrorMessage("'quantity' must be present and cannot be less than or equal to zero"),
                    new ErrorMessage("'price' must be present and cannot be less than or equal to zero"),
                    new ErrorMessage("'userId' must be present"),
                    new ErrorMessage("'timestamp' must be present")
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
            .price(BigDecimal.TEN)
            .quantity(0L)
            .timestamp(Instant.now())
            .build();


        try {
            limitOrderValidator.validate(bid);
        } catch (ValidationException ve) {

            assertThat(ve.getFailures()).hasSize(1);

            assertThat(ve.getFailures()).contains(
                new ErrorMessage("'quantity' must be present and cannot be less than or equal to zero")
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
            .price(BigDecimal.TEN)
            .quantity(-2L)
            .timestamp(Instant.now())
            .build();


        try {
            limitOrderValidator.validate(bid);
        } catch (ValidationException ve) {

            assertThat(ve.getFailures()).hasSize(1);

            assertThat(ve.getFailures()).contains(
                new ErrorMessage("'quantity' must be present and cannot be less than or equal to zero")
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
            .price(BigDecimal.ZERO)
            .quantity(20L)
            .timestamp(Instant.now())
            .build();


        try {
            limitOrderValidator.validate(bid);
        } catch (ValidationException ve) {

            assertThat(ve.getFailures()).hasSize(1);

            assertThat(ve.getFailures()).contains(
                new ErrorMessage("'price' must be present and cannot be less than or equal to zero")
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
            .price(BigDecimal.valueOf(-3))
            .quantity(20L)
            .timestamp(Instant.now())
            .build();


        try {
            limitOrderValidator.validate(bid);
        } catch (ValidationException ve) {

            assertThat(ve.getFailures()).hasSize(1);

            assertThat(ve.getFailures()).contains(
                new ErrorMessage("'price' must be present and cannot be less than or equal to zero")
            );

            throw ve;
        }

    }

}