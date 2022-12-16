package CheckRunner.entity;

import CheckRunner.entity.receiptbuilder.ContentService;
import CheckRunner.entity.receiptbuilder.TitleService;

import java.util.HashMap;

public class CashReceiptWithoutDiscountCard implements CashReceipt {

    private final TitleService titleService;
    private final ContentService contentService;

    private String ending;

    public CashReceiptWithoutDiscountCard(HashMap<Product, Integer> listOfAllCheckPositions) {
        this.titleService = new TitleService();
        this.contentService = new ContentService(listOfAllCheckPositions);
    }

    @Override
    public void printCashReceipt() {
        String title = titleService.addAllTitleStrings();
        String content = contentService.addAllContentStrings();
        System.out.println(title);
        System.out.println(content);
        System.out.println(ending);
    }
}
