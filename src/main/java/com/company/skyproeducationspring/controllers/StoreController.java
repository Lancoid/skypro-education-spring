package com.company.skyproeducationspring.controllers;

import com.company.skyproeducationspring.exceptions.EmptyOrderAddException;
import com.company.skyproeducationspring.exceptions.EmptyStoreCartException;
import com.company.skyproeducationspring.services.store.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/store")
public class StoreController {
    private final ShoppingCartService shoppingCartService;

    public StoreController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping(path = "/order/add")
    public String orderAdd(@RequestParam String[] ids, Model model) {
        if (ids.length == 0) {
            throw new EmptyOrderAddException("Нет товаров для добавления");
        }

        Map<Integer, Long> orders = shoppingCartService.add(ids);

        model.addAttribute("orders", orders);

        log.info("StoreService: add {}", orders);

        return "orders/add";
    }

    @GetMapping(path = "/order/get")
    public String orderGet(Model model) {
        Map<Integer, Long> orders = shoppingCartService.get();

        if (orders.size() == 0) {
            throw new EmptyStoreCartException("Нет товаров в корзине");
        }

        model.addAttribute("orders", orders);

        log.info("StoreService: get {}", orders);

        return "orders/get";
    }
}
