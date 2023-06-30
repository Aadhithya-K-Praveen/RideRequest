package com.example.riderequest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.junit.Assert;

import java.util.Objects;

public class MyStepdefs {
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> response;
    private String apiUrl;
    private String baseUrl = "http://localhost:8082";




    @When("I make a GET request to the API endpoint")
    public void iMakeARequestToTheAPIEndpoint() {
        String apiUrl = baseUrl + "rides";
        response = restTemplate.getForEntity(apiUrl, String.class);
    }




    @Given("the current API {string} is available")
    public void theCurrentAPIIsAvailable(String arg0) {

        apiUrl = baseUrl + arg0;
        response = restTemplate.getForEntity(apiUrl, String.class);
        Assert.assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @When("I make a POST request to the {string} API endpoint")
    public void iMakeAPOSTRequestToTheAPIEndpoint(String arg0) {
        // Prepare the request body and headers
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();

        requestBody.add("name", "AKp");
        requestBody.add("phoneno", "432423433");
        requestBody.add("source", "Velachery");
        requestBody.add("destination", "Perungudi");
        requestBody.add("time", "12:00");
        requestBody.add("date", "26-06-23");
        requestBody.add("passengerCount", "2");
        // Create the request body as needed
                HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request entity with the body and headers
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        apiUrl = baseUrl + arg0;
        // Send the POST request
        RestTemplate restTemplate = new RestTemplate();
        response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
    }

    @Then("I should receive a successful response with status code {int}")
    public void iShouldReceiveASuccessfulResponseWithStatusCode(int arg0) {
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Then("I should receive a successful response with {string}")
    public void iShouldReceiveASuccessfulResponseWith(String arg0) {

        Assert.assertTrue(Objects.requireNonNull(response.getBody()).contains("Request Successful"));
    }


}
