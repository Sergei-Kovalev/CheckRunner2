package CheckRunner.exceptions;

public class DiscountCardNotFountException extends RuntimeException {
    private final int cardNumber;

    public DiscountCardNotFountException(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String getMessage() {
        return "Discount card number: " + cardNumber + " not found";
    }
}
