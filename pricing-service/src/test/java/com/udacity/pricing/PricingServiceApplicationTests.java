package com.udacity.pricing;

import com.udacity.pricing.domain.price.Price;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters

public class PricingServiceApplicationTests {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<Price> json;


	@Before
	public void setup() throws Exception {
		Price price = getPrice();
		price.setId(1L);
		mvc.perform(
				post(new URI("/prices"))
						.content(json.write(price).getJson())
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isCreated());
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void getPriceById() throws Exception {
		ResultActions actions = mvc.perform(get("/prices/1"))
				.andExpect(status().isOk());
		Price price = getPrice();
		actions.andExpect(jsonPath("currency", is(price.getCurrency())));
		actions.andExpect(jsonPath("price", equalTo(price.getPrice().doubleValue())));
		actions.andExpect(jsonPath("vehicleId", is(price.getVehicleId().intValue())));
	}

	public Price getPrice() {
		Price price = new Price();
		price.setCurrency("USD");
		price.setPrice(new BigDecimal(1000f));
		price.setVehicleId(5L);
		return price;
	}
}
