package io.freddief.marketplace.service;

import io.freddief.marketplace.domain.Bid;
import io.freddief.marketplace.exception.NotFoundException;
import io.freddief.marketplace.repository.BidRepository;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BidServiceTest {

    @Mock
    private BidRepository bidRepository;
    @Mock
    private LimitOrderValidator limitOrderValidator;
    @InjectMocks
    private BidService bidService;

    private Bid bid;

    @Before
    public void setup () {
        bid = Bid.builder()
            .id("id")
            .build();
    }

    @Test
    public void create_callsValidator() {
        bidService.create(bid);
        verify(limitOrderValidator).validate(bid);
    }

    @Test
    public void create_callsRepository() {
        bidService.create(bid);
        verify(bidRepository).create(bid);
    }

    @Test
    public void create_returnsBid() {

        when(bidRepository.create(any())).thenReturn(bid);

        Bid returned = bidService.create(this.bid);

        assertThat(returned).isEqualTo(bid);
    }

    @Test
    public void findAllByUserId_callsRepository() {
        bidService.findAllByUserId("userId");
        verify(bidRepository).findAllByUserId("userId");
    }

    @Test
    public void findAllByUserId_returnsList() {
        when(bidRepository.findAllByUserId(any())).thenReturn(Lists.newArrayList(bid));
        List<Bid> bids = bidService.findAllByUserId("userId");
        assertThat(bids).isEqualTo(bids);
    }

    @Test
    public void findHighestBidByItemId_callsRepository() {

        when(bidRepository.findAllByItemId(anyString())).thenReturn(Lists.newArrayList(mock(Bid.class)));

        bidService.findHighestBidByItemId("item");

        verify(bidRepository).findAllByItemId("item");

    }

    @Test
    public void findHighestBidByItemId_findsHighest() {

        Bid bid1 = mock(Bid.class);
        Bid bid2 = mock(Bid.class);
        Bid bid3 = mock(Bid.class);

        when(bid1.getPrice()).thenReturn(BigDecimal.valueOf(1));
        when(bid2.getPrice()).thenReturn(BigDecimal.valueOf(3));
        when(bid3.getPrice()).thenReturn(BigDecimal.valueOf(2));

        when(bidRepository.findAllByItemId(anyString())).thenReturn(Lists.newArrayList(bid1, bid2, bid3));

        Bid returned = bidService.findHighestBidByItemId("item");

        assertThat(returned).isEqualTo(bid2);
    }

    @Test(expected = NotFoundException.class)
    public void findHighestBidByItemId_whenNoBids_throwException() {
        bidService.findHighestBidByItemId("item");
    }

}