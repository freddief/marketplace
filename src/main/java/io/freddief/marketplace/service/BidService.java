package io.freddief.marketplace.service;

import io.freddief.marketplace.domain.Bid;
import io.freddief.marketplace.repository.BidRepository;
import io.freddief.marketplace.validator.BidValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BidService {

    private final BidRepository bidRepository;
    private final BidValidator bidValidator;

    @Autowired
    public BidService(BidRepository bidRepository,
                      BidValidator bidValidator) {
        this.bidRepository = bidRepository;
        this.bidValidator = bidValidator;
    }

    public Bid create(Bid bid) {
        bidValidator.validate(bid);
        return bidRepository.create(bid);
    }

}

