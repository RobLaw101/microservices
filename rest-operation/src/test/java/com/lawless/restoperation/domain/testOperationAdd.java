package com.lawless.restoperation.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class testOperationAdd {
    @Test
    public void testAdd() {
        OperationStrategy op = new OperationAdd();
        assertThat(op.doArithmeticOperation(2,6), is(8.0));

        assertThat(op.doArithmeticOperation(2,-6), is(-4.0));

    }
}
