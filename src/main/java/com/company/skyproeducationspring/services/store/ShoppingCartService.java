package com.company.skyproeducationspring.services.store;

import java.util.Map;

public interface ShoppingCartService {
    Map<Integer, Long> add(String[] ids);

    Map<Integer, Long> get();
}
