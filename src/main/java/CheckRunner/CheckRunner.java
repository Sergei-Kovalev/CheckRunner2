package CheckRunner;

import CheckRunner.dao.DataPlaceholderHandler;
import CheckRunner.entity.DiscountCard;
import CheckRunner.entity.Product;
import CheckRunner.receipts.CashReceipt;
import CheckRunner.receipts.CashReceiptSwitcher;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckRunner {

    public static void main(String[] args) {


        Parser parser = new Parser(args);

        //Заполняем данные всех карт
        ArrayList<DiscountCard> allDiscountCards = DataPlaceholderHandler.fillDiscountCardList();
        //Заполняем данные всех продуктов
        ArrayList<Product> allProducts = DataPlaceholderHandler.fillProductsList();

        //находим номер карты и мапу (id товара, количество)
        int discountCardNumber = parser.findDiscountCardNumber();
        HashMap<Integer, Integer> map = parser.getAllCheckLines();

        //находим номер карты - если нет то будет null
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
