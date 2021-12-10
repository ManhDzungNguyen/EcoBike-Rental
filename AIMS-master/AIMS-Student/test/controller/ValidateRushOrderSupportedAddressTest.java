package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class ValidateRushOrderSupportedAddressTest extends Object {
    private PlaceOrderController placeOrderController;
    @BeforeEach
    void setUp() throws Exception {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
            "Hà Nội,true",
            "Hồ Chí Minh,false",
            "Quảng Ninh,false",
            "Kiên Giang,false"
    })
    public void test(String deliveryTime, boolean expected) {
        boolean isValid = placeOrderController.validateRushOrderSupportedAddress(deliveryTime);
        assertEquals(expected, isValid);
    }
}