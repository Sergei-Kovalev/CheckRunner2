package CheckRunner.receipts;

import CheckRunner.entity.DiscountCard;
import CheckRunner.entity.Product;
import CheckRunner.receiptbuilder.*;

import java.util.HashMap;

public class CashReceiptWithDiscountCard implements CashReceipt {

    private final TitleService titleService;
    private final ContentService contentService;
    private final DiscountFromActionLineService discountFromActionLineService;
    private final DiscountFromDiscountCardService discountFromDiscountCardService;
    private final EndingService endingService;

    public CashReceiptWithDiscountCard(HashMap<Product, Integer> listOfAllCheckPositions, DiscountCard discountCard) {
        CashReceiptCalculator calculator = new CashReceiptCalculator(listOfAllCheckPositions);
        HashMap<Product, Integer> listWithDiscount = calculator.revaluationFromCard(discountCard);
        this.titleService = new TitleService();
        this.contentService = new ContentService(listWithDiscount);
        this.discountFromActionLineService = new DiscountFromActionLineService(listWithDiscount);
        this.discountFromDiscountCardService = new DiscountFromDiscountCardService(listWithDiscount, discountCard);
        this.endingService = new EndingService(listWithDiscount);
    }

    @Override
    public void printCashReceipt() {
        String title = titleService.addAllTitleStrings();
        String content = contentService.addAllContentStrings();
        String discountFromActionLine = discountFromActionLineService.addDiscountByActionStrings();
        String discountFromCardLine = discountFromDiscountCardService.addDiscountByDiscountCardStrings();
        String ending = endingService.addAllEndingStrings();
        System.out.println(title);
        System.out.println(content);
        System.out.println(discountFromActionLine);
        System.out.println(discountFromCardLine);
        System.out.println(ending);
    }
}
