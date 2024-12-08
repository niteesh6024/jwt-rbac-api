package com.coding_sphere.jwt_rbac_api.payload.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageResponseTest {

    @Test
    void testConstructorAndGetter() {
        MessageResponse messageResponse = new MessageResponse("Success");

        assertEquals("Success", messageResponse.getMessage());
    }

    @Test
    void testSetter() {
        MessageResponse messageResponse = new MessageResponse("message");
        messageResponse.setMessage("Updated Message");

        assertEquals("Updated Message", messageResponse.getMessage());
    }

    @Test
    void testFalseMessage() {
        MessageResponse messageResponse = new MessageResponse("false");

        assertNotEquals("Updated Success Message", messageResponse.getMessage());
    }
}
