package CheckRunner.receiptbuilder;

import CheckRunner.config.Config;
import CheckRunner.entity.DiscountCard;
import CheckRunner.entity.Product;
import CheckRunner.exceptions.InsufficientCashReceiptWidth;

import java.util.HashMap;

public class DiscountFromDiscountCardService {
    private static final int CASH_RECEIPT_WIDTH = Integer.parseInt(Config.getProperty(Config.CASH_RECEIPT_WIDTH));
    private static final String LINE_DISCOUNT = "Card DISCOUNT:";
    private final HashMap<Product, Integer> listOfAllCheckPositions;
    private final DiscountCard discountCard;

    public DiscountFromDiscountCardService(HashMap<Product, Integer> listOfAllCheckPositions, DiscountCard discountCard) {
        this.listOfAllCheckPositions = listOfAllCheckPositions;
        this.discountCard = discountCard;
    }


    public String addDiscountByDiscountCardStrings() {
        return  createDiscountLineFromCard(listOfAllCheckPositions, discountCard);
    }


    private static String createDiscountLineFromCard(HashMap<Product, Integer> listOfAllCheckPositions, DiscountCard discountCard) {
        CashReceiptCalculator calculator = new CashReceiptCalculator(listOfAllCheckPositions);
        double discountValue = calculator.calculateDiscountFromCard(discountCard);
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
