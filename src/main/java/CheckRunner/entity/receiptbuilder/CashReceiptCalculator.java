package CheckRunner.entity.receiptbuilder;

import CheckRunner.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class CashReceiptCalculator {
    private final HashMap<Product, Integer> listOfAllCheckPositions;

    public CashReceiptCalculator(HashMap<Product, Integer> listOfAllCheckPositions) {
        this.listOfAllCheckPositions = listOfAllCheckPositions;
    }

    public double calculateDiscount() {
        double discount = 0;
        for (Map.Entry<Product, Integer> entry : listOfAllCheckPositions.entrySet()) {
            if (entry.getKey().isOnAction() && entry.getKey().isOnAction()) {
                discount += entry.getValue() * entry.getKey().getPrice() * 0.1;
            }
        }
        return discount;
    }
}
