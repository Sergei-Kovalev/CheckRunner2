package CheckRunner.receiptbuilder;

import CheckRunner.config.Config;
import CheckRunner.entity.Product;
import CheckRunner.exceptions.InsufficientCashReceiptWidth;

import java.util.HashMap;

public class DiscountFromActionLineService {
    private static final int CASH_RECEIPT_WIDTH = Integer.parseInt(Config.getProperty(Config.CASH_RECEIPT_WIDTH));
    private static final String LINE_DISCOUNT = "Action DISCOUNT:";
    private final HashMap<Product, Integer> listOfAllCheckPositions;

    public DiscountFromActionLineService(HashMap<Product, Integer> listOfAllCheckPositions) {
        this.listOfAllCheckPositions = listOfAllCheckPositions;
    }

    public String addDiscountByActionStrings() {
        return  createDiscountLineFromAction(listOfAllCheckPositions);
    }


    private static String createDiscountLineFromAction(HashMap<Product, Integer> listOfAllCheckPositions) {
        CashReceiptCalculator calculator = new CashReceiptCalculator(listOfAllCheckPositions);
        double discountValue = calculator.calculateDiscountFromAction();
        StringBuilder stringBuilder = new StringBuilder();
        int width = CASH_RECEIPT_WIDTH - LINE_DISCOUNT.length() - 9;
        if (width >= 0) {
            stringBuilder.append(LINE_DISCOUNT);
            stringBuilder.append(" ".repeat(width));
            stringBuilder.append(String.format("%9s", "$" + String.format("%.2f",discountValue)));
        } else {
            throw new InsufficientCashReceiptWidth(CASH_RECEIPT_WIDTH);
        }
        return stringBuilder.toString();
    }
}
