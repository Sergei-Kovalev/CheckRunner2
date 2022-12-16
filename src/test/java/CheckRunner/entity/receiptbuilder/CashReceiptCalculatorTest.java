package CheckRunner.entity.receiptbuilder;

import CheckRunner.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CashReceiptCalculatorTest {

    @Test
    void calculateDiscount1() {
        Product product = new Product(1, "Mars", 1.48, true);
        HashMap<Product, Integer> list = new HashMap<>();
        list.put(product, 5);
        CashReceiptCalculator calculator = new CashReceiptCalculator(list);

        Assertions.assertEquals("0,74", String.format("%.2f", calculator.calculateDiscount()));
    }

    @Test
    void calculateDiscount2() {
        Product product = new Product(1, "Mars", 1.48, false);
        HashMap<Product, Integer> list = new HashMap<>();
        list.put(product, 5);
        CashReceiptCalculator calculator = new CashReceiptCalculator(list);

        Assertions.assertEquals("0,00", String.format("%.2f", calculator.calculateDiscount()));
    }
}