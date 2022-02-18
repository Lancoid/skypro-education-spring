package com.company.skyproeducationspring;

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
    @Test
    @Description("Check service correct `hello()` processing")
    public void correctWithoutUsername() {
        HelloServiceInterface service = new HelloService();
        Assertions.assertEquals("<b>Hello</b>", service.hello());
    }

    @Description("Check service correct `hello(String username)` processing")
    @ParameterizedTest
    @MethodSource("correctWithUsernameDataProvider")
    public void correctWithUsername(String username, String expected) {
        HelloServiceInterface service = new HelloService();
        Assertions.assertEquals(expected, service.hello(username));
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------------- */

    public static Object[][] correctWithUsernameDataProvider() {
        return new Object[][]{
                {"username", "<b>Hello, username</b>"},
                {"Vasya", "<b>Hello, Vasya</b>"},
                {"232324TbiDbiSh", "<b>Hello, 232324TbiDbiSh</b>"}
        };
    }
}
