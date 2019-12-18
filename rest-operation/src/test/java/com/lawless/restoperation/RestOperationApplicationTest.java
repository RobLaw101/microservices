/*package com.lawless.restoperation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class RestOperationApplicationTest {

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    // Inject which port we were assigned
    @Value("${server.port}")
    int port;



    @Test
    public void testAddValues() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/add?x=3&y=2"), HttpMethod.GET, entity, String.class);
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        String expected = "5.0";

        assertThat(actual, is(expected));
    }

    @Test
    public void testMulValues() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/multiply?x=3&y=2"), HttpMethod.GET, entity, String.class);
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        String expected = "6.0";

        assertThat(actual, is(expected));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


}*/
