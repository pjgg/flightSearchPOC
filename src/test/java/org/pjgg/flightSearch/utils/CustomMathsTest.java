package org.pjgg.flightSearch.utils;

import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomMathsTest {

    @Test
    public void basicUsageTest() {

        //invoke
        final double result = CustomMaths.applyPercentage(20, 100);

        //Asserts
        assertTrue(result == 20);

    }

}
