package io.freddief.marketplace.repository;


import io.freddief.marketplace.domain.Bid;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InMemoryBidRepositoryTest {

    @Test
    public void findAllByUserId() {

        Bid bid1 = mock(Bid.class);
        Bid bid2 = mock(Bid.class);
        Bid bid3 = mock(Bid.class);

        when(bid1.getUserId()).thenReturn("user1");
        when(bid2.getUserId()).thenReturn("user2");
        when(bid3.getUserId()).thenReturn("user1");

        InMemoryBidRepository inMemoryBidRepository = new InMemoryBidRepository();

        inMemoryBidRepository.create(bid1);
        inMemoryBidRepository.create(bid2);
        inMemoryBidRepository.create(bid3);

        List<Bid> returned = inMemoryBidRepository.findAllByUserId("user1");

        assertThat(returned).hasSize(2);
        assertThat(returned).contains(bid1, bid3);

    }

    @Test
    public void findAllByItemId() {

        Bid bid1 = mock(Bid.class);
        Bid bid2 = mock(Bid.class);
        Bid bid3 = mock(Bid.class);

        when(bid1.getItemId()).thenReturn("item1");
        when(bid2.getItemId()).thenReturn("item2");
        when(bid3.getItemId()).thenReturn("item1");

        InMemoryBidRepository inMemoryBidRepository = new InMemoryBidRepository();

        inMemoryBidRepository.create(bid1);
        inMemoryBidRepository.create(bid2);
        inMemoryBidRepository.create(bid3);

        List<Bid> returned = inMemoryBidRepository.findAllByItemId("item1");

        assertThat(returned).hasSize(2);
        assertThat(returned).contains(bid1, bid3);
    }

}