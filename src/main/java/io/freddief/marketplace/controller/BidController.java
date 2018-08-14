package io.freddief.marketplace.controller;

import io.freddief.marketplace.dto.Bid;
import io.freddief.marketplace.dto.CreateBid;
import io.freddief.marketplace.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bids")
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

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public List<Bid> findAllByUserId(@PathVariable String userId) {
        return bidService.findAllByUserId(userId)
            .stream()
            .map(Bid::fromDomain)
            .collect(Collectors.toList());
    }

    @RequestMapping(value = "/items/{itemId}/highest", method = RequestMethod.GET)
    public Bid findHighestBidByItemId(@PathVariable String itemId) {
        return Bid.fromDomain(bidService.findHighestBidByItemId(itemId));
    }

}
