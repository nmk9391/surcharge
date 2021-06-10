package com.assignment.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class RateControllerTests {

	@Autowired
	private MockMvc mvc;
	
	private final static String USERID = "ABC123";

	@Test
	void testGetAllRates() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.get("/api/v1/surcharge/getAllRates").with(user(USERID)).with(csrf()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.length()", greaterThan(1)));
	}

	@Test
	void testAddRate() throws Exception {

		String content = "{\"rateId\": 20,\"rateDesc\": \"YYY\",\"rateEffDate\": \"2021-01-01T14:00:00\",\"rateExpDate\": \"2021-12-31T23:59:00\",\"amt\": 100}";

		mvc.perform(MockMvcRequestBuilders.post("/api/v1/surcharge/addRate").with(user(USERID)).with(csrf()).content(content).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.amt", is(100)));
	}

	@Test
	void testUpdateRate() throws Exception {

		String content = "{\"rateId\": 1,\"rateDesc\": \"YYY\",\"rateEffDate\": \"2021-01-01T14:00:00\",\"rateExpDate\": \"2021-12-31T23:59:00\",\"amt\": 300}";

		mvc.perform(MockMvcRequestBuilders.put("/api/v1/surcharge/updateRate").with(user(USERID)).with(csrf()).content(content).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.amt", is(300)));
	}

	@Test
	void testSearchRate() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.get("/api/v1/surcharge/searchRate/20").with(user(USERID)).with(csrf()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.rateSO.amt", is(100)));
	}
	
	@Test
	void testDeleteRate() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.delete("/api/v1/surcharge/deleteRate/20").with(user(USERID)).with(csrf()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", is("Deleted")));
	}
}
