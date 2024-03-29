package com.bright.talk.api;

import com.bright.talk.exception.BadRequestException;
import com.bright.talk.exception.Message;
import com.bright.talk.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ApiExceptionHandlerTest {

    private ApiExceptionHandler apiExceptionHandler;

    @Before
    public void setUp() {
        apiExceptionHandler = new ApiExceptionHandler();
    }

    @Test
    public void methodResourceNotFoundException_CorrectResponse() {
        ResourceNotFoundException resourceNotFoundException
            = new ResourceNotFoundException("test mesage");
        ErrorResponse response
            = apiExceptionHandler.methodResourceNotFoundException(resourceNotFoundException);
        assertNotNull(response);
        assertEquals(resourceNotFoundException.getMessage(), response.getCode());
    }

    @Test
    public void methodBadRequest_CorrectResponse() {
        BadRequestException badRequestException = new BadRequestException("test mesage");
        ErrorResponse response
            = apiExceptionHandler.methodBadRequest(badRequestException);
        assertNotNull(response);
        assertEquals(badRequestException.getMessage(), response.getCode());
    }

    @Test
    public void methodArgumentNotValidException_CorrectResponse() {
        ErrorResponse response = apiExceptionHandler.methodArgumentNotValidException(
            new MethodArgumentNotValidException(
                null,
                new BeanPropertyBindingResult("message", "test_class_name")
            ));

        assertNotNull(response);
        assertEquals(Message.INVALID_PARAMS.getText(), response.getCode());
    }

}