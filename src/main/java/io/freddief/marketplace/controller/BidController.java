package io.freddief.marketplace.controller;

import io.freddief.marketplace.dto.Bid;
import io.freddief.marketplace.dto.CreateBid;
import io.freddief.marketplace.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bid")
public class BidController {

    private final BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Bid create(@RequestBody CreateBid createBid) {
        return Bid.fromDomain(
            bidService.create(
                createBid.toDomain())
        );
    }

}
