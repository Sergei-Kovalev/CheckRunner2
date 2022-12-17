package CheckRunner.receipts;

import CheckRunner.entity.Product;
import CheckRunner.receiptbuilder.ContentService;
import CheckRunner.receiptbuilder.DiscountFromActionLineService;
import CheckRunner.receiptbuilder.TitleService;

import java.util.HashMap;

public class CashReceiptWithoutDiscountCard implements CashReceipt {

    private final TitleService titleService;
    private final ContentService contentService;
    private final DiscountFromActionLineService discountFromActionLineService;

    private String ending;

    public CashReceiptWithoutDiscountCard(HashMap<Product, Integer> listOfAllCheckPositions) {
        this.titleService = new TitleService();
        this.contentService = new ContentService(listOfAllCheckPositions);
        this.discountFromActionLineService = new DiscountFromActionLineService(listOfAllCheckPositions);
    }

    @Override
    public void printCashReceipt() {
        String title = titleService.addAllTitleStrings();
        String content = contentService.addAllContentStrings();
        String discountFromActionLine = discountFromActionLineService.addDiscountByActionStrings();
        System.out.println(title);
        System.out.println(content);
        System.out.println(discountFromActionLine);
        System.out.println(ending);
    }
}