package com.deal.domain.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationInput {

    private String fieldName;

    private String fnName;

    private String[] args;
}
