package com.bright.talk.api;

import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.Map;

//Class was copied from the last project to save a time
public class ExpectedResults {
    private ValidatableResponse response;

    ExpectedResults(ValidatableResponse response, ExpectedResult expectedResult) {
        this.response = response;
        if (expectedResult != null) {
            expectedResult.check(this);
        }
    }

    public ExpectedResults responseCodeIsOk() {
        response.statusCode(200);
        return this;
    }

    public ExpectedResults responseCodeIsCreated() {
        response.statusCode(201);
        return this;
    }

    public ExpectedResults responseCodeIsNotFound() {
        response.statusCode(404);
        return this;
    }

    public ExpectedResults responseCodeIsBadRequest() {
        response.statusCode(400);
        return this;
    }

    public ExpectedResults responseBodyContains(
        HashMap<String, Object> data
    ) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            response.body(entry.getKey(), Matchers.equalTo(entry.getValue()));
        }

        return this;
    }

    public ExpectedResults responseFieldMatches(String field, Object value) {
        response.body(field, Matchers.equalTo(value));
        return this;
    }

    public ExpectedResults and() {
        return this;
    }

    public ExpectedResults and(ExpectedResult expectedResult) {
        expectedResult.check(this);
        return this;
    }

    public ExpectedResults and(Runnable expectedResult) {
        expectedResult.run();
        return this;
    }

    public ExpectedResults that() {
        return this;
    }

    public interface ExpectedResult {
        void check(ExpectedResults results);
    }
}
