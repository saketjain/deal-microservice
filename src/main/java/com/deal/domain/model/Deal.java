package com.deal.domain.model;

import com.deal.domain.validation.Validation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deal {

    @Validation(fnName="ValidateCounterparty", args={"/counterparty"})
    private String counterparty;

    @Validation(fnName="ValidateOurparty", args={"/ourparty"})
    private String ourparty;

    @Validation(fnName="ValidateTrader", args={"/trader/firstName", "/trader/lastName"})
    private Trader trader;

}
