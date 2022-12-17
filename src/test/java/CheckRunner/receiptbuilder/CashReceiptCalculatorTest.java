package CheckRunner.receiptbuilder;

import CheckRunner.entity.DiscountCard;
import CheckRunner.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CashReceiptCalculatorTest {

    @Test
    void calculateDiscountFromAction1() {
        Product product = new Product(1, "Mars", 1.48, true);
        HashMap<Product, Integer> list = new HashMap<>();
        list.put(product, 5);
        CashReceiptCalculator calculator = new CashReceiptCalculator(list);

        Assertions.assertEquals("0,74", String.format("%.2f", calculator.calculateDiscountFromAction()));
    }

    @Test
    void calculateDiscountFromAction2() {
        Product product = new Product(1, "Mars", 1.48, false);
        HashMap<Product, Integer> list = new HashMap<>();
        list.put(product, 5);
        CashReceiptCalculator calculator = new CashReceiptCalculator(list);

        Assertions.assertEquals("0,00", String.format("%.2f", calculator.calculateDiscountFromAction()));
    }

    @Test
    void calculateDiscountFromCard() {
        Product product = new Product(1, "Mars", 100, false);
        DiscountCard discountCard = new DiscountCard(1, 1234, 10);
        HashMap<Product, Integer> list = new HashMap<>();
        list.put(product, 1);
        CashReceiptCalculator calculator = new CashReceiptCalculator(list);

        Assertions.assertEquals(10, calculator.calculateDiscountFromCard(discountCard));
    }

}