package com.lawless.restoperation.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class testOperationMul {
    @Test
    public void testMul() {
        OperationStrategy op = new OperationMul();
        assertThat(op.doArithmeticOperation(2,-6), is(-12.0));

        assertThat(op.doArithmeticOperation(-6,-6), is(36.0));

    }
}
