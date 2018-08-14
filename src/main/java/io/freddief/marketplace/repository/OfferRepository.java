package io.freddief.marketplace.repository;

import io.freddief.marketplace.domain.Offer;

import java.util.List;

public interface OfferRepository {

    Offer create(Offer offer);

    List<Offer> findAllByUserId(String userId);

    List<Offer> findAllByItemId(String itemId);

    void deleteAll();

}
