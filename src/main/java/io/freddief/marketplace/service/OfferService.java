package io.freddief.marketplace.service;

import io.freddief.marketplace.domain.Offer;
import io.freddief.marketplace.exception.NotFoundException;
import io.freddief.marketplace.repository.OfferRepository;
import io.freddief.marketplace.validator.LimitOrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class OfferService {

    private final OfferRepository offerRepository;
    private final LimitOrderValidator limitOrderValidator;

    @Autowired
    public OfferService(OfferRepository offerRepository,
                        LimitOrderValidator limitOrderValidator) {
        this.offerRepository = offerRepository;
        this.limitOrderValidator = limitOrderValidator;
    }

    public Offer create(Offer offer) {
        limitOrderValidator.validate(offer);
        return offerRepository.create(offer);
    }

    public List<Offer> findAllByUserId(String userId) {
        return offerRepository.findAllByUserId(userId);
    }

    public Offer findLowestOfferByItemId(String itemId) {
       return offerRepository.findAllByItemId(itemId)
            .stream()
            .min(Comparator.comparing(Offer::getPrice))
            .orElseThrow(() -> new NotFoundException("no offers in the order book"));
    }

}

