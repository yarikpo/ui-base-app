package ua.clamor1s.task911;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.clamor1s.task911.controller.CardController;
import ua.clamor1s.task911.controller.OperationController;
import ua.clamor1s.task911.dto.CardDetailsDto;
import ua.clamor1s.task911.dto.CardSaveDto;
import ua.clamor1s.task911.dto.OperationSaveDto;
import ua.clamor1s.task911.dto.RestResponse;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = Task911Application.class)
@AutoConfigureMockMvc
class Task911ApplicationTests {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private CardController cardController;
	@Autowired
	private OperationController operationController;
	@Autowired
	private ObjectMapper objectMapper;

	@AfterEach
	public void afterEach() {
		operationController.deleteAll();
		cardController.deleteAll();
	}

	@Test
	@Order(1)
	void testGetAllOperations() throws Exception { // This test has to be run on its own
		afterEach();
		cardController.createCard(new CardSaveDto("Adam", "Merser", "8873482437", 23562, Date.valueOf(LocalDate.of(2022, 11, 16))));
//		Thread.sleep(3000);
		operationController.createOperation(new OperationSaveDto(1, 20000, Timestamp.valueOf("2018-09-01 09:01:15")));
		operationController.createOperation(new OperationSaveDto(1, 20000, Timestamp.valueOf("2018-09-01 09:01:15")));
		operationController.createOperation(new OperationSaveDto(1, 20000, Timestamp.valueOf("2018-09-01 09:01:15")));


		MvcResult mvcResult = mvc.perform(get("/api/operations")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String expected = """
				{
					"operations": 
						[
							{
								"operationId": 1,
								"cardId": 1,
								"amount": 20000
							},
							{
								"operationId": 2,
								"cardId": 1,
								"amount": 20000
							},
							{
								"operationId": 3,
								"cardId":1,
								"amount":20000
							}
						]
					}
				""".replaceAll("\\s", "");

		RestResponse response = new RestResponse(mvcResult.getResponse().getContentAsString());
		assertThat(response.getResult()).isEqualTo(expected);
	}

	@Test
	void contextLoads() {
	}



	@Test
	void testCreateCard() throws Exception {
		String name = "Adam";
		String surname = "Merser";
		String code = "8873482437";
		Integer cvv = 23562;
		String creationDate = "2022-11-16";

		String body = """
				{
				    "name": "%s",
				    "surname": "%s",
				    "code": "%s",
				    "cvv": %d,
				    "creationDate": "%s"
				}
				""".formatted(name, surname, code, cvv, creationDate);

		MvcResult mvcResult = mvc.perform(post("/api/cards")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isCreated())
				.andReturn();

		RestResponse response = parseResponse(mvcResult, RestResponse.class);
		int cardId = Integer.parseInt(response.getResult());
		assertThat(cardId).isGreaterThanOrEqualTo(1);

		try {
			CardDetailsDto dto = cardController.getCardById(cardId);
			assertThat(dto.getName()).isEqualTo(name);
			assertThat(dto.getSurname()).isEqualTo(surname);
			assertThat(dto.getCode()).isEqualTo(code);
			assertThat(dto.getCvv()).isEqualTo(cvv);
			assertThat(dto.getCreationDate().toString()).isEqualTo(creationDate);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testCreateCardBadRequest() throws Exception {
		mvc.perform(post("/api/cards")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testPostGetMultipleFieldsCards() throws Exception {
		cardController.createCard(new CardSaveDto("Adam", "Merser", "8873482437", 23562, Date.valueOf(LocalDate.of(2022, 11, 16))));
		cardController.createCard(new CardSaveDto("Adam", "Merser", "8873482437", 23562, Date.valueOf(LocalDate.of(2022, 11, 16))));
		cardController.createCard(new CardSaveDto("Alex", "Merser", "8873482437", 23562, Date.valueOf(LocalDate.of(2022, 11, 16))));
		cardController.createCard(new CardSaveDto("Adam", "Merser", "8873482437", 23562, Date.valueOf(LocalDate.of(2022, 11, 16))));
		cardController.createCard(new CardSaveDto("Adam", "Merser", "8873482437", 23562, Date.valueOf(LocalDate.of(2022, 11, 16))));

		String content = """
				{
				    "code": "8873482437",
				    "name": "Adam"
				}
				""";

		MvcResult mvcResult = mvc.perform(post("/api/cards/_search/0")
						.contentType(MediaType.APPLICATION_JSON)
						.content(content))
				.andExpect(status().isOk())
				.andReturn();

		RestResponse response = parseGetResponse(mvcResult);
		String expected = """
				{
					"cards": [
						{
							"cardId": 1,
							"name": "Adam",
							"surname": "Merser"
						},
						{
							"cardId": 2,
							"name": "Adam",
							"surname": "Merser"
						},
						{
							"cardId": 4,
							"name": "Adam",
							"surname": "Merser"
						}
					]
				}
				""".replaceAll("\\s", "");

		assertThat(response.getResult()).isEqualTo(expected);
	}

	@Test
	void testUpdateCardById() throws Exception {
		String name1 = "Adam";
		String surname1 = "Merser";
		String code1 = "8873482437";
		Integer cvv1 = 23562;
		String creationDate1 = "2022-11-16";

		String body1 = """
				{
				    "name": "%s",
				    "surname": "%s",
				    "code": "%s",
				    "cvv": %d,
				    "creationDate": "%s"
				}
				""".formatted(name1, surname1, code1, cvv1, creationDate1);


		MvcResult mvcResult = mvc.perform(post("/api/cards")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body1))
				.andExpect(status().isCreated())
				.andReturn();

		RestResponse response = parseResponse(mvcResult, RestResponse.class);
		int cardId = Integer.parseInt(response.getResult());
		assertThat(cardId).isGreaterThanOrEqualTo(1);

		String name2 = "Alex2";
		String surname2 = "Merser";
		String code2 = "8873482437";
		Integer cvv2 = 66666;
		String creationDate2 = "2022-11-23";
		String body2 = """
				{
				    "name": "%s",
				    "surname": "%s",
				    "code": "%s",
				    "cvv": %d,
				    "creationDate": "%s"
				}
				""".formatted(name2, surname2, code2, cvv2, creationDate2);

		MvcResult mvcResult2 = mvc.perform(put("/api/cards/" + cardId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body2))
				.andExpect(status().isOk())
				.andReturn();

		RestResponse response2 = parseResponse(mvcResult2, RestResponse.class);
		assertThat("OK").isEqualTo(response2.getResult());

		try {
			CardDetailsDto dto = cardController.getCardById(cardId);
			assertThat(dto.getName()).isEqualTo(name2);
			assertThat(dto.getSurname()).isEqualTo(surname2);
			assertThat(dto.getCode()).isEqualTo(code2);
			assertThat(dto.getCvv()).isEqualTo(cvv2);
			assertThat(dto.getCreationDate().toString()).isEqualTo(creationDate2);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testUpdateCard_BadRequest() throws Exception {
		mvc.perform(put("/api/cards")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{}"))
				.andExpect(status().isMethodNotAllowed());
	}




	private RestResponse parseGetResponse(MvcResult mvcResult) throws UnsupportedEncodingException {

		return new RestResponse(mvcResult.getResponse().getContentAsString());

	}

	private <T>T parseResponse(MvcResult mvcResult, Class<T> c) {
		try {
			return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), c);
		} catch (JsonProcessingException | UnsupportedEncodingException e) {
			throw new RuntimeException("Error parsing json", e);
		}
	}

}
