package CheckRunner;

import CheckRunner.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        double price = 1.48 * (1.0 - 4.0 / 100);
        double scale = Math.pow(10, 2);
        double roundedPrice = (Math.round(price * scale)) / scale;

        System.out.println(roundedPrice);
    }
}
