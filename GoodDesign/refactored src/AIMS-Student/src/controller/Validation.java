package controller;

import common.exception.InvalidDeliveryInfoException;
import common.exception.InvalidRushOrderDeliveryInfoException;

import java.io.IOException;
import java.util.HashMap;

public class Validation {

    public Validation() {
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
}
