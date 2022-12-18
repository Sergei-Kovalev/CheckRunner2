package CheckRunner;

import CheckRunner.dataplaceholders.FillDataFromClass;
import CheckRunner.entity.DiscountCard;
import CheckRunner.entity.Product;
import CheckRunner.exceptions.DiscountCardNotFountException;
import CheckRunner.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

class ParserTest {

    @Test
    void findDiscountCardNumber1() {
        String[] args = new String[]{"3-1", "2-5", "5-1", "card-1234"};
        Parser parser = new Parser(args);
        Assertions.assertEquals(1234, parser.findDiscountCardNumber());
    }
    @Test
    void findDiscountCardNumber2() {
        String[] args = new String[]{"3-1", "2-5", "5-1"};
        Parser parser = new Parser(args);
        Assertions.assertEquals(0, parser.findDiscountCardNumber());
    }

    @Test
    void getAllCheckLines1() {
        String[] args = new String[]{"3-1", "2-5", "5-1", "card-1234"};
        Parser parser = new Parser(args);
        HashMap<Integer, Integer> expectedMap = new HashMap<>();
        expectedMap.put(3, 1);
        expectedMap.put(2, 5);
        expectedMap.put(5, 1);
        Assertions.assertEquals(expectedMap, parser.getAllCheckLines());
    }

    @Test
    void getAllCheckLines2() {
        String[] args = new String[]{"3-1", "2-5", "5-1"};
        Parser parser = new Parser(args);
        HashMap<Integer, Integer> expectedMap = new HashMap<>();
        expectedMap.put(3, 1);
        expectedMap.put(2, 5);
        expectedMap.put(5, 1);
        Assertions.assertEquals(expectedMap, parser.getAllCheckLines());
    }

    @Test
    void findDiscountCardByNumber1() {
        String[] args = new String[]{"3-1", "2-5", "5-1", "card-1234"};
        Parser parser = new Parser(args);
        int cardNumber = parser.findDiscountCardNumber();

        ArrayList<DiscountCard> allDiscountCards = new ArrayList<>();
        FillDataFromClass.fillDiscountCardList(allDiscountCards);
        DiscountCard expectedCard = new DiscountCard(1, 1234, 4);


        Assertions.assertEquals(expectedCard.getNumber(),parser.findDiscountCardByNumber(cardNumber, allDiscountCards).getNumber());
        Assertions.assertEquals(expectedCard.getDiscountValue(),parser.findDiscountCardByNumber(cardNumber, allDiscountCards).getDiscountValue());
        Assertions.assertEquals(expectedCard.getId(),parser.findDiscountCardByNumber(cardNumber, allDiscountCards).getId());
    }

    @Test
    void findDiscountCardByNumber2() {
        String[] args = new String[]{"3-1", "2-5", "5-1"};
        Parser parser = new Parser(args);
        int cardNumber = parser.findDiscountCardNumber();

        ArrayList<DiscountCard> allDiscountCards = new ArrayList<>();
        FillDataFromClass.fillDiscountCardList(allDiscountCards);
        Exception exception = new DiscountCardNotFountException(cardNumber);

        Assertions.assertThrows(DiscountCardNotFountException.class, () -> parser.findDiscountCardByNumber(cardNumber, allDiscountCards));

        Assertions.assertEquals("Discount card number: 0 not found", exception.getMessage());
    }

    @Test
    void findProductById1() {
        String[] args = new String[]{"3-1", "2-5", "5-1", "card-1234"};
        Parser parser = new Parser(args);
        int id = 1;

        ArrayList<Product> allProducts = new ArrayList<>();
        FillDataFromClass.fillProductList(allProducts);

        Product product = new Product(1, "Mars", 1.48, false);
        Assertions.assertEquals(product.getId(), parser.findProductById(id, allProducts).getId());
        Assertions.assertEquals(product.getName(), parser.findProductById(id, allProducts).getName());
        Assertions.assertEquals(product.getPrice(), parser.findProductById(id, allProducts).getPrice());
        Assertions.assertEquals(product.isOnAction(), parser.findProductById(id, allProducts).isOnAction());

    }

    @Test
    void findProductById2() {
        String[] args = new String[]{"3-1", "2-5", "5-1", "card-1234"};
        Parser parser = new Parser(args);
        int id = 999;

        ArrayList<Product> allProducts = new ArrayList<>();
        FillDataFromClass.fillProductList(allProducts);
        Exception exception = new ProductNotFoundException(id);

        Assertions.assertThrows(ProductNotFoundException.class, () -> parser.findProductById(id, allProducts));

        Assertions.assertEquals("Product with id = 999 not found", exception.getMessage());

    }
}