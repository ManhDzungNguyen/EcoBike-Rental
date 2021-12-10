package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import common.exception.InvalidRushOrderDeliveryInfoException;
import entity.cart.Cart;
import entity.cart.CartMedia;
import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;
import views.screen.popup.PopupScreen;

/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author nguyenlm
 */
public class PlaceOrderController extends BaseController{

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the avalibility of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException{
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   cartMedia.getQuantity(), 
                                                   cartMedia.getPrice());    
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
    }

    /**
     * This method takes responsibility for processing the rush order shipping info from user
     * @param info rush order shipping information
     * @throws InterruptedException
     * @throws IOException
     */
    public void processRushOrderDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateRushOrderDeliveryInfo(info);
    }

    /**
   * The method validates the delivery info
   * @param info shipping information
   * @throws InterruptedException
   * @throws IOException
   */
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
        if(!validateName(info.get("name"))) throw new InvalidDeliveryInfoException("Invalid name");
        if(!validatePhoneNumber(info.get("phone"))) throw new InvalidDeliveryInfoException("Invalid phone number");
        if(!validateAddress(info.get("address"))) throw new InvalidDeliveryInfoException("Invalid address");
    }

    /**
     * The method validates the rush order delivery info
     * @param info rush order shipping information
     * @throws InterruptedException
     * @throws IOException
     */
    public void validateRushOrderDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
        if(!validateName(info.get("name"))) throw new InvalidRushOrderDeliveryInfoException("Invalid name");
        if(!validatePhoneNumber(info.get("phone"))) throw new InvalidRushOrderDeliveryInfoException("Invalid phone number");
        if(!validateAddress(info.get("address"))) throw new InvalidRushOrderDeliveryInfoException("Invalid address");
        if(!validateRushOrderSupportedAddress(info.get("province"))) {
            throw new InvalidRushOrderDeliveryInfoException("Address haven't supported rush order");
        }
        if(!validateRushOrderDeliveryTime(info.get("time"))) {
            throw new InvalidRushOrderDeliveryInfoException("Invalid delivery time");
        }
    }

    /**
     * The method validates the user phone number
     * @param phoneNumber user phone number
     * @return validation result
     */
    public boolean validatePhoneNumber(String phoneNumber) {
    	if (phoneNumber.length() != 10) return false;
        if (!phoneNumber.startsWith("0")) return false;

        try{
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e){
            return false;
        }
    	return true;
    }

    /**
     * The method validates the user name
     * @param name user name
     * @return validation result
     */
    public boolean validateName(String name) {
        if (name == null) return false;
        boolean result = name.matches("[a-zA-Z][a-zA-Z ]+");
    	return result;
    }

    /**
     * The method validates the user address
     * @param address user address
     * @return validation result
     */
    public boolean validateAddress(String address) {
        if (address == null) return false;
        boolean result = address.matches("[a-zA-Z0-9][a-zA-Z0-9., ]+");
        return result;
    }

    /**
     * The method validates the user province support rush order or not
     * @param province user province
     * @return validation result
     */
    public boolean validateRushOrderSupportedAddress(String province) {
        if (province.equals("Hà Nội")) return true;
        return false;
    }

    /**
     * The method validates the user delivery time
     * @param deliveryTime user rush order delivery time
     * @return validation result
     */
    public boolean validateRushOrderDeliveryTime(String deliveryTime) {
        if (deliveryTime == null) return false;
        boolean result = deliveryTime.matches("[a-zA-Z0-9][a-zA-Z0-9.:/ ]+");
        return result;
    }

    /**
     * This method calculates the shipping fees of order
     * @param order
     * @return shippingFee
     */
    public int calculateShippingFee(Order order){
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }

    /**
     * This method calculates the shipping fees of rush order
     * @param order
     * @return Rush order shipping fee
     */
    public int calculateRushOrderShippingFee(Order order){
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() ) + 10 * order.getNumberOfMedia();
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
