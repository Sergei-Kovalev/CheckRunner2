package CheckRunner.entity.receiptbuilder;

import CheckRunner.config.Config;
import CheckRunner.entity.Product;
import CheckRunner.exceptions.InsufficientCashReceiptWidth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentService {
    //ширина чека в символах для печати set in properties
    private static final int CASH_RECEIPT_WIDTH = Integer.parseInt(Config.getProperty(Config.CASH_RECEIPT_WIDTH));
    private static final String LINE_1_1 = "QTY DESCRIPTION";
    private static final String LINE_1_2 = " PRICE    TOTAL";
    private static final String LINE_DISCOUNT = "DISCOUNT by action:";

    private final HashMap<Product, Integer> listOfAllCheckPositions;
//    private int totalDiscountByAction = calculateDiscount();

    public ContentService(HashMap<Product, Integer> listOfAllCheckPositions) {
        this.listOfAllCheckPositions = listOfAllCheckPositions;
    }

    public String addAllContentStrings() {
        return createLine1() + System.lineSeparator()
                + createProductLines(listOfAllCheckPositions)
                + "_".repeat(CASH_RECEIPT_WIDTH) + System.lineSeparator()
                + "_".repeat(CASH_RECEIPT_WIDTH) + System.lineSeparator()
                + createDiscountLine(listOfAllCheckPositions) + System.lineSeparator();
    }

    private static String createLine1() {

        StringBuilder stringBuilder = new StringBuilder();
        int width = CASH_RECEIPT_WIDTH - LINE_1_1.length() - LINE_1_2.length();

        if (width >= 0) {
            stringBuilder.append(LINE_1_1);
            stringBuilder.append(" ".repeat(width));
            stringBuilder.append(LINE_1_2);
        } else {
            throw new InsufficientCashReceiptWidth(CASH_RECEIPT_WIDTH);
        }
        return stringBuilder.toString();
    }

    private static String createProductLines(HashMap<Product, Integer> listOfAllCheckPositions) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Product, Integer> entry : listOfAllCheckPositions.entrySet()) {
            stringBuilder.append(createLineFromPosition(entry.getKey(), entry.getValue()));
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private static String createLineFromPosition(Product product, Integer quantity) {
        double totalPrice = product.getPrice() * quantity;
        if (quantity >= 5 && product.isOnAction()) {
            totalPrice *= 0.9;
        }
        StringBuilder stringBuilder = new StringBuilder();
        quantityPosition(quantity, stringBuilder);
        productNamePosition(product, stringBuilder);
        stringBuilder.append(String.format("%6s", "$" + product.getPrice()));
        stringBuilder.append(String.format("%9s", "$" + String.format("%.2f",totalPrice)));
        return stringBuilder.toString();
    }

    private static String createDiscountLine(HashMap<Product, Integer> listOfAllCheckPositions) {
        CashReceiptCalculator calculator = new CashReceiptCalculator(listOfAllCheckPositions);
        double discountValue = calculator.calculateDiscount();
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

    private static void productNamePosition(Product product, StringBuilder stringBuilder) {
        int width = CASH_RECEIPT_WIDTH - LINE_1_2.length() - 4;                         // 4 - длина строки "QTY "
        int productNameLength = product.getName().length();
        if (productNameLength > width) {
            String productName = product.getName();
            List<String> strings = new ArrayList<>();
            for (int i = 0; i < productName.length(); i += width) {
                strings.add(productName.substring(i, Math.min(productName.length(), i + width)));
            }
            stringBuilder.append(strings.get(0));
            stringBuilder.append(System.lineSeparator());
            for (int i = 1; i < strings.size() - 2; i++) {
                stringBuilder.append(" ".repeat(4));
                stringBuilder.append(strings.get(i));
                stringBuilder.append(" ".repeat(15));
            }
            stringBuilder.append(" ".repeat(4));
            stringBuilder.append(strings.get(strings.size() - 1));
            stringBuilder.append(" ".repeat(9));

        } else {
            stringBuilder.append(product.getName());
            stringBuilder.append(" ".repeat(width - product.getName().length()));
        }
    }

    private static void quantityPosition(Integer quantity, StringBuilder stringBuilder) {
        if (quantity.toString().length() == 1) {
            stringBuilder.append(" ");
            stringBuilder.append(quantity);
            stringBuilder.append("  ");
        } else if (quantity.toString().length() == 2) {
            stringBuilder.append(quantity);
            stringBuilder.append("  ");
        } else if (quantity.toString().length() == 3) {
            stringBuilder.append(quantity);
            stringBuilder.append(" ");
        }
    }


}
