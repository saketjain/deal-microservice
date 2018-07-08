package com.deal.domain.validation;

import com.deal.domain.model.Deal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ValidationService {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Map<String, List<Validation>> validationMap = new HashMap<>();

    public ValidationService() {
        collectValidations();
    }

    private void collectValidations(){
        for(Field field: Deal.class.getDeclaredFields()) {
            if(field.isAnnotationPresent(Validation.class)){
                List<Validation> validations = Arrays.asList(field.getAnnotationsByType(Validation.class));
                validationMap.put(field.getName(), validations);
            }
        }
    }

    public List<ValidationInput> createValidationInput(Deal deal) {
        List<ValidationInput> validationInputList = new ArrayList<>();
        JsonNode jsonNode = objectMapper.valueToTree(deal);
        validationMap.entrySet().stream().forEach(me -> {
           List<Validation> validations = me.getValue();
           validations.stream().forEach(v -> {
               List<String> values = Arrays.stream(v.args()).map( p -> jsonNode.at(p).asText()).collect(Collectors.toList());
               validationInputList.add(new ValidationInput(me.getKey(), v.fnName(), values.toArray(new String[]{})));
           });
        });
        return validationInputList;
    }
}
