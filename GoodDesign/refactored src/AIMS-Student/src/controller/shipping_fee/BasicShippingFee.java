package controller.shipping_fee;

import entity.order.Order;
import java.util.Random;


public class BasicShippingFee implements ShippingFeeCalculatorInterface{
    private Order order;

    public BasicShippingFee(Order order) {
        this.order = order;
    }
    @Override
    public int calculateShippingFee() {
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
        return fees;
    }
}
