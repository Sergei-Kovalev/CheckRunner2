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


    public HashMap<Product, Integer> revaluationFromCard(DiscountCard discountCard) {
        HashMap<Product, Integer> listWithDiscount =new HashMap<>();
        for (Map.Entry<Product, Integer> entry : listOfAllCheckPositions.entrySet()) {
            Product product = new Product(
                    entry.getKey().getId(),
                    entry.getKey().getName(),
                    getRoundedPrice(discountCard, entry),
                    entry.getKey().isOnAction());

            listWithDiscount.put(product, entry.getValue());
        }
        return listWithDiscount;
    }

    private double getRoundedPrice(DiscountCard discountCard, Map.Entry<Product, Integer> entry) {
        double price = entry.getKey().getPrice() * (1.0 - discountCard.getDiscountValue() / 100.0);
        double scale = Math.pow(10, 2);
        return (Math.round(price * scale)) / scale;
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

    public double calculateTotalForVat() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : listOfAllCheckPositions.entrySet()) {
            if (entry.getValue() >= 5 && entry.getKey().isOnAction()) {
                double value = entry.getValue() * entry.getKey().getPrice() *
                        (1 - Double.parseDouble(Config.getProperty(Config.ACTION_DISCOUNT_VALUE)));
                double scale = Math.pow(10, 2);
                total += (Math.round(value * scale)) / scale;
            } else {
                double value = entry.getValue() * entry.getKey().getPrice();
                double scale = Math.pow(10, 2);
                total += (Math.round(value * scale)) / scale;
            }
        }
        return total;
    }

    public double calculateVat() {
        return calculateTotalForVat() * Double.parseDouble(Config.getProperty(Config.VAT_VALUE)) / 100;
    }

    public double calculateTotal() {
        return calculateTotalForVat() + calculateVat();
    }
}
