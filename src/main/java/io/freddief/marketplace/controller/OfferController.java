package io.freddief.marketplace.controller;

import io.freddief.marketplace.dto.CreateOffer;
import io.freddief.marketplace.dto.Offer;
import io.freddief.marketplace.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Offer create(@RequestBody CreateOffer createOffer) {
        return Offer.fromDomain(
            offerService.create(
                createOffer.toDomain())
        );
    }

}
