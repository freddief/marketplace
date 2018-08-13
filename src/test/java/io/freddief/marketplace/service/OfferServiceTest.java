package io.freddief.marketplace.service;

import io.freddief.marketplace.domain.Offer;
import io.freddief.marketplace.repository.OfferRepository;
import io.freddief.marketplace.validator.LimitOrderValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceTest {

    @Mock
    private OfferRepository offerRepository;
    @Mock
    private LimitOrderValidator limitOrderValidator;
    @InjectMocks
    private OfferService offerService;

    private Offer offer;

    @Before
    public void setup () {
        offer = Offer.builder()
            .id("id")
            .build();
    }

    @Test
    public void create_callsValidator() {
        offerService.create(offer);
        verify(limitOrderValidator).validate(offer);
    }

    @Test
    public void create_callsRepository() {
        offerService.create(offer);
        verify(offerRepository).create(offer);
    }

    @Test
    public void create_returnsOffer() {

        when(offerRepository.create(any())).thenReturn(offer);

        Offer returned = offerService.create(this.offer);

        assertThat(returned).isEqualTo(offer);
    }
}