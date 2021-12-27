package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class ValidateAddressTest extends Object {
    private PlaceOrderController placeOrderController;
    @BeforeEach
    void setUp() throws Exception {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
            "698 Candlewood Lane Cabot Cove Maine,true",
            "1313 Mockingbird Lane Mockingbird Heights,true",
            "abc123#,false",
            "1234567890a3&,false"
    })
    public void test(String address, boolean expected) {
        boolean isValid = placeOrderController.validateAddress(address);
        assertEquals(expected, isValid);
    }
}