package io.freddief.marketplace.service;

import io.freddief.marketplace.domain.Bid;
import io.freddief.marketplace.repository.BidRepository;
import io.freddief.marketplace.validator.BidValidator;
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
public class BidServiceTest {

    @Mock
    private BidRepository bidRepository;
    @Mock
    private BidValidator bidValidator;
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
        verify(bidValidator).validate(bid);
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

}