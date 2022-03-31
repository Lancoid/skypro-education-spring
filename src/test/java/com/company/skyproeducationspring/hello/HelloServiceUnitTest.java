package com.company.skyproeducationspring.hello;

import com.company.skyproeducationspring.services.hello.HelloService;
import com.company.skyproeducationspring.services.hello.HelloServiceInterface;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Hello Service Test")
@Epic("HelloService")
public class HelloServiceUnitTest {
    public static Object[][] correctWithUsernameDataProvider() {
        return new Object[][]{
                {"username", "Hello, username"},
                {"Vasya", "Hello, Vasya"},
                {"232324TbiDbiSh", "Hello, 232324TbiDbiSh"}
        };
    }

    @Test
    @Description("Check service correct `hello()` processing")
    public void correctWithoutUsername() {
        HelloServiceInterface service = new HelloService();
        Assertions.assertEquals("Hello", service.hello());
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------------- */

    @Description("Check service correct `hello(String username)` processing")
    @ParameterizedTest
    @MethodSource("correctWithUsernameDataProvider")
    public void correctWithUsername(String username, String expected) {
        HelloServiceInterface service = new HelloService();
        Assertions.assertEquals(expected, service.hello(username));
    }
}
