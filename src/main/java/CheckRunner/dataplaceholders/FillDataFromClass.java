package CheckRunner.dataplaceholders;

import CheckRunner.entity.DiscountCard;
import CheckRunner.entity.Product;

import java.util.ArrayList;

public class FillDataFromClass {

    public static void fillProductList(ArrayList<Product> allProducts) {
        allProducts.add(new Product(1, "Mars", 1.48, false));
        allProducts.add(new Product(2, "Snickers", 1.99, true));
        allProducts.add(new Product(3, "Bounty", 1.59, false));
        allProducts.add(new Product(4, "Nuts", 27.59, false));
        allProducts.add(new Product(5, "Maize", 0.99, false));
        allProducts.add(new Product(6, "Rice", 1.22, false));
        allProducts.add(new Product(7, "Fish", 22.11, false));
        allProducts.add(new Product(8, "Orange", 10.22, false));
        allProducts.add(new Product(9, "Big Papa Jones Corn in little package", 1.59, true));
        allProducts.add(new Product(10, "Tomato juice", 9.22, false));
        allProducts.add(new Product(11, "Lemon", 0.77, false));
        allProducts.add(new Product(12, "Chicken meat", 6.55, false));
        allProducts.add(new Product(13, "Beef meat", 9.29, false));
        allProducts.add(new Product(14, "Milk 2.5%", 2.59, false));
        allProducts.add(new Product(15, "Milk 3.5%", 2.99, false));
        allProducts.add(new Product(16, "Cheese", 15.69, false));
        allProducts.add(new Product(17, "Corn", 3.25, true));
        allProducts.add(new Product(18, "Bread black", 2.01, false));
        allProducts.add(new Product(19, "Bread white", 2.51, false));
        allProducts.add(new Product(20, "Donuts", 3.55, false));
        allProducts.add(new Product(21, "Cookie Mams Breakfast", 5.59, true));
        allProducts.add(new Product(22, "Cookie Grandmas Dinner", 6.99, true));
    }

    public static void fillDiscountCardList(ArrayList<DiscountCard> allDiscountCards) {
        allDiscountCards.add(new DiscountCard(1, 1234, 4));
        allDiscountCards.add(new DiscountCard(2, 1235, 2));
        allDiscountCards.add(new DiscountCard(3, 1236, 1));
        allDiscountCards.add(new DiscountCard(4, 1237, 4));
        allDiscountCards.add(new DiscountCard(5, 1238, 7));
        allDiscountCards.add(new DiscountCard(6, 1239, 4));
        allDiscountCards.add(new DiscountCard(7, 1240, 1.5));
        allDiscountCards.add(new DiscountCard(8, 1241, 2.3));
        allDiscountCards.add(new DiscountCard(9, 1242, 5.5));
        allDiscountCards.add(new DiscountCard(10, 1243, 4.5));
        allDiscountCards.add(new DiscountCard(11, 1244, 3));
        allDiscountCards.add(new DiscountCard(12, 1245, 0.5));
    }
}
