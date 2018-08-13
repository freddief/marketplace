package io.freddief.marketplace.repository;

import com.google.common.collect.Lists;
import io.freddief.marketplace.domain.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryBidRepository implements BidRepository {

    private final List<Bid> bids;

    @Autowired
    public InMemoryBidRepository() {
        bids = Lists.newArrayList();
    }

    @Override
    public Bid create(Bid bid) {
        bids.add(bid);
        return bid;
    }

}
