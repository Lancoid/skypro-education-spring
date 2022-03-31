package com.company.skyproeducationspring.shoppingCart;

import com.company.skyproeducationspring.services.store.ShoppingCartService;
import com.company.skyproeducationspring.services.store.ShoppingCartServiceImpl;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;

@DisplayName("ShoppingCart Service Test")
@Epic("ShoppingCartService")
public class ShoppingCartServiceUnitTest {
    public static Object[][] correctAddAndGetDataProvider() {
        return new Object[][]{
                {
                        new String[]{"1", "1", "1"},
                        new HashMap<Integer, Long>() {{
                            put(1, 3L);
                        }}
                },
                {
                        new String[]{"1", "2", "3"},
                        new HashMap<Integer, Long>() {{
                            put(1, 1L);
                            put(2, 1L);
                            put(3, 1L);
                        }}
                },
                {
                        new String[]{"1", "2", "1"},
                        new HashMap<Integer, Long>() {{
                            put(1, 2L);
                            put(2, 1L);
                        }}
                }
        };
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------------- */

    @Description("Check service correct ONCE `add(String[] ids)` and ONCE `get` processing")
    @ParameterizedTest
    @MethodSource("correctAddAndGetDataProvider")
    public void correctOnceAddAndGet(String[] input, Map<Integer, Long> expected) {
        ShoppingCartService service = new ShoppingCartServiceImpl();

        Assertions.assertEquals(expected, service.add(input));
        Assertions.assertEquals(expected, service.get());
    }

    @Description("Check service correct MULTIPLY `add(String[] ids)` and ONCE `get` processing")
    @Test
    public void correctMultiplyAddAndGet() {
        ShoppingCartService service = new ShoppingCartServiceImpl();

        service.add(new String[]{"1", "1", "1"});
        service.add(new String[]{"1", "2", "1"});
        service.add(new String[]{"1", "2", "3"});

        Map<Integer, Long> expected = new HashMap<>() {{
            put(1, 6L);
            put(2, 2L);
            put(3, 1L);
        }};

        Assertions.assertEquals(expected, service.get());
    }
}
