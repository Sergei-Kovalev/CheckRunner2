package CheckRunner.dataplaceholders;

import CheckRunner.entity.DiscountCard;
import CheckRunner.entity.Product;

import java.io.*;
import java.util.ArrayList;

public class FillDataFromFile {

    public static void fillProductList(ArrayList<Product> allProducts) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] productParameters = line.trim().split(", ");
                Product product = new Product(
                        Integer.parseInt(productParameters[0]),
                        productParameters[1],
                        Double.parseDouble(productParameters[2]),
                        Boolean.parseBoolean(productParameters[3])
                );
                allProducts.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fillDiscountCardList(ArrayList<DiscountCard> allDiscountCards) {
        try (BufferedReader reader = new BufferedReader(new FileReader("DiscountCards.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] discountCardParameters = line.trim().split(", ");
                DiscountCard discountCard = new DiscountCard(
                        Integer.parseInt(discountCardParameters[0]),
                        Integer.parseInt(discountCardParameters[1]),
                        Double.parseDouble(discountCardParameters[2])
                );
                allDiscountCards.add(discountCard);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
