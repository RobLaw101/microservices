package com.lawless.restoperation.service;

import com.lawless.restoperation.domain.OperationStrategy;
import com.lawless.restoperation.domain.OperationAdd;
import com.lawless.restoperation.domain.OperationMul;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {
    private static OperationStrategy adder = new OperationAdd();
    private static OperationStrategy multiplier = new OperationMul();


    @Override
    public Number add(Number x, Number y) {
        return adder.doArithmeticOperation(x, y);
    }

    @Override
    public Number mul(Number x, Number y) {
        return multiplier.doArithmeticOperation(x, y);
    }
}
