package io.freddief.marketplace.repository;


import io.freddief.marketplace.domain.Offer;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InMemoryOfferRepositoryTest {

    @Test
    public void findAllByUserId() {

        Offer offer1 = mock(Offer.class);
        Offer offer2 = mock(Offer.class);
        Offer offer3 = mock(Offer.class);

        when(offer1.getUserId()).thenReturn("user1");
        when(offer2.getUserId()).thenReturn("user2");
        when(offer3.getUserId()).thenReturn("user1");

        InMemoryOfferRepository inMemoryOfferRepository = new InMemoryOfferRepository();

        inMemoryOfferRepository.create(offer1);
        inMemoryOfferRepository.create(offer2);
        inMemoryOfferRepository.create(offer3);

        List<Offer> returned = inMemoryOfferRepository.findAllByUserId("user1");

        assertThat(returned).contains(offer1, offer3);

    }

}