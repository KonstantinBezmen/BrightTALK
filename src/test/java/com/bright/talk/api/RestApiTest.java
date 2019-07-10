package com.bright.talk.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//Class was copied from the last project to save a time
public class RestApiTest {
    private String url;

    private Integer port = 8080;

    private ContentType contentType = ContentType.JSON;

    private Object requestBody;

    private RequestMethod requestMethod = RequestMethod.GET;

    private RestApiTest(String url) {
        this.url = url;
    }

    public static RestApiTest sendRequestToEndpoint(String url) {
        return new RestApiTest(url);
    }

    public RestApiTest usingPort(Integer port) {
        this.port = port;
        return this;
    }

    @SuppressWarnings("unused")
    public RestApiTest usingContentType(ContentType contentType) {
        this.contentType = contentType;
        return this;
    }

    public RestApiTest usingGetMethod() {
        this.requestMethod = RequestMethod.GET;
        return this;
    }

    public RestApiTest usingPostMethod() {
        this.requestMethod = RequestMethod.POST;
        return this;
    }

    public RestApiTest usingPostMethod(Object requestBody) {
        this.requestMethod = RequestMethod.POST;
        this.requestBody = requestBody;
        return this;
    }

    public RestApiTest usingPutMethod() {
        this.requestMethod = RequestMethod.PUT;
        return this;
    }

    public RestApiTest usingPutMethod(Object requestBody) {
        this.requestMethod = RequestMethod.PUT;
        this.requestBody = requestBody;
        return this;
    }

    public RestApiTest withBody(Object requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public RestApiTest and() {
        return this;
    }

    public RestApiTest usingDeleteMethod() {
        this.requestMethod = RequestMethod.DELETE;
        return this;
    }

    public RestApiTest asNotAuthenticatedUser() {
        return this;
    }



    public ExpectedResults andCheckThat() {
        return andCheckThat(null);
    }

    @SuppressWarnings("WeakerAccess")
    public ExpectedResults andCheckThat(ExpectedResults.ExpectedResult expectedResult) {
        RequestSpecification spec = RestAssured.given()
            .port(port)
            .contentType(contentType);

        if (requestBody != null) {
            spec.body(requestBody);
        }

        Response response;
        switch (requestMethod) {
            case GET:
                response = spec.get(url);
                break;
            case PUT:
                response = spec.put(url);
                break;
            case DELETE:
                response = spec.delete(url);
                break;
            case POST:
                response = spec.post(url);
                break;
            default:
                response = spec.get(url);
        }

        if (expectedResult != null) {
            return new ExpectedResults(response.then(), expectedResult);
        } else {
            return new ExpectedResults(response.then(), null);
        }

    }

    protected enum RequestMethod {
        GET, POST, PUT, DELETE
    }
}
