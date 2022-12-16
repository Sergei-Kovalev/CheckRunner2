package CheckRunner;

import CheckRunner.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Product product = new Product(9, "Big Papa Jones Corn in little package", 1.59, true);
        String productName = product.getName();
        int width = 8;

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < productName.length(); i += width) {
            strings.add(productName.substring(i, Math.min(productName.length(), i + width)));
        }

        System.out.println(strings);
    }
}
