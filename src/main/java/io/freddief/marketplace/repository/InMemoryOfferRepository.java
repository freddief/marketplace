package io.freddief.marketplace.repository;

import com.google.common.collect.Lists;
import io.freddief.marketplace.domain.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryOfferRepository implements OfferRepository {

    private final List<Offer> offers;

    @Autowired
    public InMemoryOfferRepository() {
        offers = Lists.newArrayList();
    }

    @Override
    public Offer create(Offer offer) {
        offers.add(offer);
        return offer;
    }

}
