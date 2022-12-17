package CheckRunner.receiptbuilder;

import CheckRunner.config.Config;
import CheckRunner.entity.DiscountCard;
import CheckRunner.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class CashReceiptCalculator {
    private final HashMap<Product, Integer> listOfAllCheckPositions;

    public CashReceiptCalculator(HashMap<Product, Integer> listOfAllCheckPositions) {
        this.listOfAllCheckPositions = listOfAllCheckPositions;
    }

    public double calculateDiscountFromAction() {
        double discount = 0;
        for (Map.Entry<Product, Integer> entry : listOfAllCheckPositions.entrySet()) {
            if (entry.getValue() >= 5 && entry.getKey().isOnAction()) {
                discount += entry.getValue() * entry.getKey().getPrice() * Double.parseDouble(Config.getProperty(Config.ACTION_DISCOUNT_VALUE));
            }
        }
        return discount;
    }

    public double calculateDiscountFromCard(DiscountCard discountCard) {
        double discount = 0;
        for (Map.Entry<Product, Integer> entry : listOfAllCheckPositions.entrySet()) {
            discount += entry.getValue() * entry.getKey().getPrice() * (discountCard.getDiscountValue() / 100);
        }
        return discount;
    }
}
