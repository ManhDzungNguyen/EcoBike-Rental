package common.exception;

/**
 * The NoRushOrderSupportedMediaException xay ra khi khong co san pham nao ho tro GHN trong cart
 *
 * @author dungnm
 */
public class NoRushOrderSupportedMediaException extends AimsException {

    private static final long serialVersionUID = 1091337136123906298L;

    public NoRushOrderSupportedMediaException() {

    }

    public NoRushOrderSupportedMediaException(String message) {
        super(message);
    }
}
