package io.freddief.marketplace.repository;

import io.freddief.marketplace.domain.Bid;

import java.util.List;

public interface BidRepository {

    Bid create(Bid bid);

    List<Bid> findAllByUserId(String userId);

}
