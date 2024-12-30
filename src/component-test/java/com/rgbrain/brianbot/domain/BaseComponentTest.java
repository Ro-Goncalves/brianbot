package com.rgbrain.brianbot.domain;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(WebEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseComponentTest {

    @LocalServerPort
    private int port;
    protected String baseUrl;

    @BeforeEach
    public void setup() {
        baseUrl = "http://localhost:" + port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    
}
