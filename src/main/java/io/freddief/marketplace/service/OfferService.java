package io.freddief.marketplace.service;

import io.freddief.marketplace.domain.Offer;
import io.freddief.marketplace.repository.OfferRepository;
import io.freddief.marketplace.validator.LimitOrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OfferService {

    private final OfferRepository bidRepository;
    private final LimitOrderValidator limitOrderValidator;

    @Autowired
    public OfferService(OfferRepository bidRepository,
                        LimitOrderValidator limitOrderValidator) {
        this.bidRepository = bidRepository;
        this.limitOrderValidator = limitOrderValidator;
    }

    public Offer create(Offer offer) {
        limitOrderValidator.validate(offer);
        return bidRepository.create(offer);
    }

    public List<Offer> findAllByUserId(String userId) {
        return bidRepository.findAllByUserId(userId);
    }

}

