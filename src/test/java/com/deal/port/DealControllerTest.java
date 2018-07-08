package com.deal.port;

import com.deal.domain.model.Deal;
import com.deal.domain.model.Trader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DealControllerTest {

    @Autowired
    private DealController dealController;

    @Test
    public void validateDealTest(){
        Deal deal = new Deal("cp1", "op1", new Trader("fn1", "ln1"));
        ResponseEntity response = dealController.validateDeal(deal);
        System.out.println(response);
    }
}
