package controller;

import controller.shipping_fee.RushOrderShippingFee;
import controller.shipping_fee.ShippingFeeCalculatorInterface;
import entity.cart.Cart;
import entity.order.Order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class PlaceRushOrderController extends PlaceOrderController{

    @Override
    public void placeOrder() throws SQLException{
        super.placeOrder();
        Cart.getCart().checkRushOrderSupportOfProduct();
    }

    @Override
    public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Rush Order Delivery Info");
        LOGGER.info(info.toString());
        validation.validateRushOrderDeliveryInfo(info);
    }

    @Override
    public int calculateShippingFee(Order order){
        ShippingFeeCalculatorInterface shippingFeeCalculator = new RushOrderShippingFee(order);
        int fees = shippingFeeCalculator.calculateShippingFee();
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
