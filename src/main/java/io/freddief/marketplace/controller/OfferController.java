package io.freddief.marketplace.controller;

import io.freddief.marketplace.dto.CreateOffer;
import io.freddief.marketplace.dto.Offer;
import io.freddief.marketplace.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/offers")
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

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public List<Offer> findAllByUserId(@PathVariable String userId) {
        return offerService.findAllByUserId(userId)
            .stream()
            .map(Offer::fromDomain)
            .collect(Collectors.toList());
    }

}
