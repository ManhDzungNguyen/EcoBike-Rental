package common.exception;

/**
 * The InvalidDeliveryInfoException wraps all unchecked exceptions You can use this
 * exception to inform
 *
 * @author dungnm
 */

public class InvalidRushOrderDeliveryInfoException extends AimsException {

    private static final long serialVersionUID = 1091337136123906298L;

    public InvalidRushOrderDeliveryInfoException() {

    }

    public InvalidRushOrderDeliveryInfoException(String message) {
        super(message);
    }
}

