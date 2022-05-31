package ar.com.intrale.client;

import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.USER_AGENT;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.intrale.FunctionBuilder;
import ar.com.intrale.FunctionConst;
import ar.com.intrale.exceptions.ClientResponseException;
import ar.com.intrale.messages.Get${msName}Request;
import ar.com.intrale.messages.Get${msName}Response;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;

@Singleton
public class ${msName}Client {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(${msName}Client.class);
	
	@Client("https://mgnr0htbvd.execute-api.us-east-2.amazonaws.com")
	@Inject
	private HttpClient httpClient;
	
   	@Inject
   	protected ObjectMapper mapper;
	
	public Get${msName}Response get(String businessname, Get${msName}Request getRequest) throws ClientResponseException, JsonMappingException, JsonProcessingException {
		
		HttpRequest<Get${msName}Request> request = HttpRequest.POST("/dev/products", getRequest)
				.header(ACCEPT, "application/json")
				.header(USER_AGENT, "Micronaut HTTP Client")
				.header(FunctionBuilder.HEADER_FUNCTION, FunctionConst.READ)
				.header(FunctionBuilder.HEADER_BUSINESS_NAME, businessname);

		try {
			HttpResponse<String> response = httpClient.toBlocking().exchange(request, String.class);
			Get${msName}Response getResponse = mapper.readValue(response.body(), Get${msName}Response.class);
			return getResponse;
		} catch (HttpClientResponseException e) {
			LOGGER.error("statusCode:" + e.getStatus().getCode());
			LOGGER.error("reason:" + e.getStatus().getReason());
			LOGGER.error("message:" + e.getMessage());
			LOGGER.error("response:" + e.getResponse());
			LOGGER.error("body:" + e.getResponse().getBody());
			throw new ClientResponseException(e.getResponse().getBody(String.class).get());
		} finally {
			httpClient.refresh();
		}
	}
	
	
}
