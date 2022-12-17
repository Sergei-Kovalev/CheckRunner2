package CheckRunner.receiptbuilder;

import CheckRunner.entity.DiscountCard;
import CheckRunner.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

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

    @Test
    void calculateTotalForVat() {
        Product product1 = new Product(1, "Mars", 1.1, false);
        Product product2 = new Product(2, "Snickers", 1.9, false);
        HashMap<Product, Integer> list = new HashMap<>();
        list.put(product1, 1);
        list.put(product2, 1);
        CashReceiptCalculator calculator = new CashReceiptCalculator(list);

        Assertions.assertEquals(3.0, calculator.calculateTotalForVat());
    }

    @Test
    void calculateVat() {
        Product product1 = new Product(1, "Mars", 1.1, false);
        Product product2 = new Product(2, "Snickers", 1.9, false);
        HashMap<Product, Integer> list = new HashMap<>();
        list.put(product1, 1);
        list.put(product2, 1);
        CashReceiptCalculator calculator = new CashReceiptCalculator(list);

        Assertions.assertEquals(0.6, calculator.calculateVat());
    }

    @Test
    void calculateTotal() {
        Product product1 = new Product(1, "Mars", 1.1, false);
        Product product2 = new Product(2, "Snickers", 1.9, false);
        HashMap<Product, Integer> list = new HashMap<>();
        list.put(product1, 1);
        list.put(product2, 1);
        CashReceiptCalculator calculator = new CashReceiptCalculator(list);

        Assertions.assertEquals(3.6, calculator.calculateTotal());
    }
}