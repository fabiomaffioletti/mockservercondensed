package me.fabiomaffioletti.msc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.DefaultResponseCreator;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.springframework.test.web.client.ExpectedCount.min;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@Log4j
@Profile("test")
public class MockRemoteServerConfiguration {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${remote.server.uri.products}")
    private String productsURI;

    @Bean
    public MockRestServiceServer mockRestServiceServer() throws Exception {
        MockRestServiceServer mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build(new NoResetRequestExpectationManager());

        byte[] responseBody = readAllBytes(get("src", "test", "resources", "mock", "response", "remote.server.request.products.findAll.200.json"));
        DefaultResponseCreator responseCreator = withStatus(HttpStatus.OK).body(responseBody).contentType(MediaType.APPLICATION_JSON_UTF8);
        mockRestServiceServer.expect(min(1), requestTo(productsURI)).andExpect(method(HttpMethod.GET)).andRespond(responseCreator);

        responseBody = readAllBytes(get("src", "test", "resources", "mock", "response", "remote.server.response.products.findOne.1.200.json"));
        responseCreator = withStatus(HttpStatus.OK).body(responseBody).contentType(MediaType.APPLICATION_JSON_UTF8);
        mockRestServiceServer.expect(min(1), requestTo(productsURI + "/1")).andExpect(method(HttpMethod.GET)).andRespond(responseCreator);

        responseBody = readAllBytes(get("src", "test", "resources", "mock", "response", "remote.server.response.products.findOne.2.200.json"));
        responseCreator = withStatus(HttpStatus.OK).body(responseBody).contentType(MediaType.APPLICATION_JSON_UTF8);
        mockRestServiceServer.expect(min(1), requestTo(productsURI + "/2")).andExpect(method(HttpMethod.GET)).andRespond(responseCreator);

        return mockRestServiceServer;
    }

}
