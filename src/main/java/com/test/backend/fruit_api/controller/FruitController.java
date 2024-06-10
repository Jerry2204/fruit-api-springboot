package com.test.backend.fruit_api.controller;

import com.test.backend.fruit_api.entity.User;
import com.test.backend.fruit_api.model.AllFruitResponse;
import com.test.backend.fruit_api.model.FruitResponse;
import com.test.backend.fruit_api.model.WebResponse;
import com.test.backend.fruit_api.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FruitController {

    @Autowired
    private FruitService fruitService;

    @GetMapping(
            path = "/api/fruits",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AllFruitResponse> getAllFruits(User user) {
        System.out.println(user);
        List<FruitResponse> fruits = fruitService.findAllFruits();

        AllFruitResponse response = new AllFruitResponse();
        response.setFruits(fruits);

        WebResponse<AllFruitResponse> webResponse = new WebResponse<>();
        webResponse.setData(response);

        return webResponse;
    }


}
