package com.udacity.pricing;

import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.domain.price.PriceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class PricingServiceApplicationIntegrationTests {
    @LocalServerPort
    private int port = 8082;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PriceRepository priceRepository;

    @Test
    public void getAllPrices() {
        ResponseEntity response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/prices/", Price.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getPriceById() {
        Price price = new Price("USD", BigDecimal.valueOf(555.555), Long.valueOf(1));
        assertEquals(1, Mockito.when(priceRepository.findById(price.getVehicleId())).thenReturn(java.util.Optional.of(price)));
    }

}
