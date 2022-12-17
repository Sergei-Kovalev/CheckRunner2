package CheckRunner;

import CheckRunner.entity.DiscountCard;
import CheckRunner.entity.Product;
import CheckRunner.receipts.CashReceipt;
import CheckRunner.receipts.CashReceiptSwitcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckRunner {

    public static void main(String[] args) {
        Parser parser = new Parser(args);

        //Заполняем данные всех карт
        List<DiscountCard> allDiscountCards = new ArrayList<>();
        FillData.fillDiscountCardList(allDiscountCards);

        //Заполняем данные всех продуктов
        List<Product> allProducts = new ArrayList<>();
        FillData.fillProductList(allProducts);

        //находим номер карты и мапу (id товара, количество)
        int discountCardNumber = parser.findDiscountCardNumber();
        HashMap<Integer, Integer> map = parser.getAllCheckLines();

        //находим номер карты - если нет то будет null                                                  *использовать для проверки потом...
        DiscountCard discountCard = null;
        if (discountCardNumber != 0) {
            discountCard = parser.findDiscountCardByNumber(discountCardNumber, allDiscountCards);
        }

        //заполняем мапу парами Товар-количество
        HashMap<Product, Integer> listOfAllCheckPositions = parser.replaceIdToProduct(allProducts, map);

        //выбираем какого типа чек будет - с применением скидочной карты либо нет
        CashReceipt cashReceipt = new CashReceiptSwitcher().switchReceiptImplementation(discountCard, listOfAllCheckPositions);


        cashReceipt.printCashReceipt();
    }
}
