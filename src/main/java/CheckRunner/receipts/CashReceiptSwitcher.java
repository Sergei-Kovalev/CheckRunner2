package CheckRunner.receipts;

import CheckRunner.entity.DiscountCard;
import CheckRunner.entity.Product;

import java.util.HashMap;

public class CashReceiptSwitcher {

    public CashReceipt switchReceiptImplementation(DiscountCard discountCard, HashMap<Product, Integer> listOfAllCheckPositions) {
        CashReceipt cashReceipt;
        if (discountCard == null) {
            cashReceipt = new CashReceiptWithoutDiscountCard(listOfAllCheckPositions);
        } else {
            cashReceipt = new CashReceiptWithDiscountCard(listOfAllCheckPositions, discountCard);
        }
        return cashReceipt;
    }
}
