package entity.cart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import common.exception.MediaNotAvailableException;
import common.exception.NoRushOrderSupportedMediaException;
import entity.media.Media;

public class Cart {
    
    private List<CartMedia> lstCartMedia;
    private static Cart cartInstance;

    public static Cart getCart(){
        if(cartInstance == null) cartInstance = new Cart();
        return cartInstance;
    }

    private Cart(){
        lstCartMedia = new ArrayList<>();
    }

    public void addCartMedia(CartMedia cm){
        lstCartMedia.add(cm);
    }

    public void removeCartMedia(CartMedia cm){
        lstCartMedia.remove(cm);
    }

    public List getListMedia(){
        return lstCartMedia;
    }

    public void emptyCart(){
        lstCartMedia.clear();
    }

    public int getTotalMedia(){
        int total = 0;
        for (Object obj : lstCartMedia) {
            CartMedia cm = (CartMedia) obj;
            total += cm.getQuantity();
        }
        return total;
    }

    public int calSubtotal(){
        int total = 0;
        for (Object obj : lstCartMedia) {
            CartMedia cm = (CartMedia) obj;
            total += cm.getPrice()*cm.getQuantity();
        }
        return total;
    }

    public void checkAvailabilityOfProduct() throws SQLException{
        boolean allAvai = true;
        for (Object object : lstCartMedia) {
            CartMedia cartMedia = (CartMedia) object;
            int requiredQuantity = cartMedia.getQuantity();
            int availQuantity = cartMedia.getMedia().getQuantity();
            if (requiredQuantity > availQuantity) allAvai = false;
        }
        if (!allAvai) throw new MediaNotAvailableException("Some media not available");
    }

    /**
     * Phuong thuc gia lap cho viec kiem tra xem cart co chua san pham co ho tro rush order hay khong
     */
    public void checkRushOrderSupportOfProduct(){
        Random r = new Random();
        boolean hasRushOrderSupportProduct = r.nextBoolean();

        if (!hasRushOrderSupportProduct) throw new NoRushOrderSupportedMediaException("No media support rush order");
    }

    public CartMedia checkMediaInCart(Media media){
        for (CartMedia cartMedia : lstCartMedia) {
            if (cartMedia.getMedia().getId() == media.getId()) return cartMedia;
        }
        return null;
    }

}
