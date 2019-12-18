package com.lawless.restoperation.domain;

public class OperationMul <T extends Number> implements OperationStrategy {

    @Override
    public Double doArithmeticOperation(Number x, Number y) {
        return x.doubleValue() * y.doubleValue();
    }
}
