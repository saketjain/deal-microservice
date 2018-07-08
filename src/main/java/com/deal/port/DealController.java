package com.deal.port;

import com.deal.domain.model.Deal;
import com.deal.domain.validation.Validation;
import com.deal.domain.validation.ValidationInput;
import com.deal.domain.validation.ValidationResponse;
import com.deal.domain.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
public class DealController {

    @Value("${validationService.host}")
    private String host;

    @Value("${validationService.port}")
    private String port;


    @Autowired
    private ValidationService validationService;

    @PostMapping("/validateDeal")
    public ResponseEntity<List<ValidationResponse>> validateDeal(@RequestBody Deal deal) {

        String validationServiceUrl = "http://" + host + ":" + port + "/validation/validate";
        List<ValidationInput> validationInput = validationService.createValidationInput(deal);
        System.out.println(validationInput);
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder.build().postForEntity(validationServiceUrl, validationInput, (Class<List<ValidationResponse>>)((Object)List.class));
    }
}
