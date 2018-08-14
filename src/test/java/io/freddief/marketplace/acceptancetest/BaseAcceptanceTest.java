package io.freddief.marketplace.acceptancetest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.freddief.marketplace.repository.BidRepository;
import io.freddief.marketplace.repository.OfferRepository;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment. RANDOM_PORT)
public abstract class BaseAcceptanceTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private OfferRepository offerRepository;

    @LocalServerPort
    private int serverPort;

    protected String formatUrl(String path) {
        return "http://localhost:" + serverPort + path;
    }

    @After
    public void teardown() {
        bidRepository.deleteAll();
        offerRepository.deleteAll();
    }

}
