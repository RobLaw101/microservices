package com.lawless.restoperation.domain;

public interface OperationStrategy<T extends Number> {
    public Number doArithmeticOperation (Number x, Number y);
}
