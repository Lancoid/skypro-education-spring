package com.company.skyproeducationspring.services.store;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@SessionScope
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final Map<Integer, Long> cart;

    public ShoppingCartServiceImpl() {
        this.cart = new HashMap<>();
    }

    @Override
    public Map<Integer, Long> add(String[] ids) {
        Map<Integer, Long> map = Arrays.stream(ids)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(id -> id, Collectors.counting()));

        map.forEach((key, value) -> this.cart.merge(key, value, Long::sum));

        return map;
    }

    @Override
    public Map<Integer, Long> get() {
        return new HashMap<>(this.cart);
    }
}
