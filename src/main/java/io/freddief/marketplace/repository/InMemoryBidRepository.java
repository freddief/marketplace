package io.freddief.marketplace.repository;

import com.google.common.collect.Lists;
import io.freddief.marketplace.domain.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Bid> findAllByUserId(String userId) {
        return bids
            .stream()
            .filter(b -> b.getUserId().equals(userId))
            .collect(Collectors.toList());
    }

    @Override
    public List<Bid> findAllByItemId(String itemId) {
        return bids
            .stream()
            .filter(b -> b.getItemId().equals(itemId))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        bids.clear();
    }
}
