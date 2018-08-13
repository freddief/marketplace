package io.freddief.marketplace.service;

import io.freddief.marketplace.domain.Bid;
import io.freddief.marketplace.repository.BidRepository;
import io.freddief.marketplace.validator.LimitOrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BidService {

    private final BidRepository bidRepository;
    private final LimitOrderValidator limitOrderValidator;

    @Autowired
    public BidService(BidRepository bidRepository,
                      LimitOrderValidator limitOrderValidator) {
        this.bidRepository = bidRepository;
        this.limitOrderValidator = limitOrderValidator;
    }

    public Bid create(Bid bid) {
        limitOrderValidator.validate(bid);
        return bidRepository.create(bid);
    }

}

