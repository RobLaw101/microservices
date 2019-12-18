package com.lawless.restoperation.domain;

public class OperationAdd<T extends Number> implements OperationStrategy {

    public Double doArithmeticOperation(Number x, Number y) {
        return x.doubleValue() + y.doubleValue();
    }
}
