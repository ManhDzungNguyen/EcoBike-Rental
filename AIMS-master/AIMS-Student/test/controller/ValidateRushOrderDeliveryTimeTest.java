package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class ValidateRushOrderDeliveryTimeTest extends Object {
    private PlaceOrderController placeOrderController;
    @BeforeEach
    void setUp() throws Exception {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
            "truoc 7 gio ngay 24.05,true",
            "$5%687fs,false",
            "truoc 22:45 ngay 24/6,true",
            "Kuu&,false"
    })
    public void test(String deliveryTime, boolean expected) {
        boolean isValid = placeOrderController.validateRushOrderDeliveryTime(deliveryTime);
        assertEquals(expected, isValid);
    }
}