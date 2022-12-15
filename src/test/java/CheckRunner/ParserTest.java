package CheckRunner;

import CheckRunner.entity.DiscountCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        List<DiscountCard> allDiscountCards = new ArrayList<>();
        FillData.fillDiscountCardList(allDiscountCards);
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

        List<DiscountCard> allDiscountCards = new ArrayList<>();
        FillData.fillDiscountCardList(allDiscountCards);
        Assertions.assertNull(parser.findDiscountCardByNumber(cardNumber, allDiscountCards));
    }
}