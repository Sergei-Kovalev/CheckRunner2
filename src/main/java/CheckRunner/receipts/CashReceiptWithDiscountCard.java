package CheckRunner.receipts;

import CheckRunner.entity.DiscountCard;
import CheckRunner.entity.Product;
import CheckRunner.receiptbuilder.ContentService;
import CheckRunner.receiptbuilder.DiscountFromActionLineService;
import CheckRunner.receiptbuilder.DiscountFromDiscountCardService;
import CheckRunner.receiptbuilder.TitleService;

import java.util.HashMap;

public class CashReceiptWithDiscountCard implements CashReceipt {

    private final TitleService titleService;
    private final ContentService contentService;
    private final DiscountFromActionLineService discountFromActionLineService;
    private final DiscountFromDiscountCardService discountFromDiscountCardService;

    private String ending;

    public CashReceiptWithDiscountCard(HashMap<Product, Integer> listOfAllCheckPositions, DiscountCard discountCard) {
        this.titleService = new TitleService();
        this.contentService = new ContentService(listOfAllCheckPositions);
        this.discountFromActionLineService = new DiscountFromActionLineService(listOfAllCheckPositions);
        this.discountFromDiscountCardService = new DiscountFromDiscountCardService(listOfAllCheckPositions, discountCard);
    }

    @Override
    public void printCashReceipt() {
        String title = titleService.addAllTitleStrings();
        String content = contentService.addAllContentStrings();
        String discountFromActionLine = discountFromActionLineService.addDiscountByActionStrings();
        String discountFromCardLine = discountFromDiscountCardService.addDiscountByDiscountCardStrings();
        System.out.println(title);
        System.out.println(content);
        System.out.println(discountFromActionLine);
        System.out.println(discountFromCardLine);
        System.out.println(ending);
    }
}
