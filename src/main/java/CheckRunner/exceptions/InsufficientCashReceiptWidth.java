package CheckRunner.exceptions;

public class InsufficientCashReceiptWidth extends RuntimeException {
    private final int receiptWidth;

    public InsufficientCashReceiptWidth(int receiptWidth) {
        this.receiptWidth = receiptWidth;
    }


    @Override
    public String getMessage() {
        return "Receipt are narrow. Width now: " + receiptWidth + ". Try to change it at application.properties file.";
    }
}
