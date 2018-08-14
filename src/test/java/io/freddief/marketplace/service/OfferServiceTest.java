package io.freddief.marketplace.service;

import io.freddief.marketplace.domain.Offer;
import io.freddief.marketplace.exception.NotFoundException;
import io.freddief.marketplace.repository.OfferRepository;
import io.freddief.marketplace.validator.LimitOrderValidator;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    public void setup() {
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

    @Test
    public void findAllByUserId_callsRepository() {
        offerService.findAllByUserId("userId");
        verify(offerRepository).findAllByUserId("userId");
    }

    @Test
    public void findAllByUserId_returnsList() {
        when(offerRepository.findAllByUserId(any())).thenReturn(Lists.newArrayList(offer));
        List<Offer> bids = offerService.findAllByUserId("userId");
        assertThat(bids).isEqualTo(bids);
    }

    @Test
    public void findLowestOfferByItemId_callsRepository() {

        when(offerRepository.findAllByItemId(anyString())).thenReturn(Lists.newArrayList(mock(Offer.class)));

        offerService.findLowestOfferByItemId("item");

        verify(offerRepository).findAllByItemId("item");

    }

    @Test
    public void findLowestOfferByItemId_findsLowestOffer() {


        Offer offer1 = mock(Offer.class);
        Offer offer2 = mock(Offer.class);
        Offer offer3 = mock(Offer.class);

        when(offer1.getPrice()).thenReturn(BigDecimal.valueOf(3));
        when(offer2.getPrice()).thenReturn(BigDecimal.valueOf(1));
        when(offer3.getPrice()).thenReturn(BigDecimal.valueOf(2));

        when(offerRepository.findAllByItemId(anyString())).thenReturn(Lists.newArrayList(offer1, offer2, offer3));

        Offer returned = offerService.findLowestOfferByItemId("itemId");

        assertThat(returned).isEqualTo(offer2);
    }

    @Test(expected = NotFoundException.class)
    public void findLowestOfferByItemId_whenNoOffers_throwException() {
        offerService.findLowestOfferByItemId("itemId");
    }

}