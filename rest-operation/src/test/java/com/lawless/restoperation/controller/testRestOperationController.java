package com.lawless.restoperation.controller;

import com.lawless.restoperation.service.OperationService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class testRestOperationController {

    @InjectMocks
    private RestOperationController restController;

    @Mock
    private OperationService service;

    @Before
    public void init() {

    }
    @Test
    public void testRestOperationController() {
        RestOperationController restController = new RestOperationController();
        String result = restController.index();
        assertEquals(result, "Welcome to Lawless Software");
    }

    @Test
    public void testAddValues() {
        when(service.add(1,2)).thenReturn(3);
        Number result = restController.addValues(1,2);

        verify(service).add(1,2);
        assertThat(result, is(3));
    }
}
