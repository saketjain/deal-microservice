package com.deal.domain.validation;

import lombok.Data;

@Data
public class ValidationResponse {

    private String fieldName;

    private boolean pass;

    private String fail;
}
