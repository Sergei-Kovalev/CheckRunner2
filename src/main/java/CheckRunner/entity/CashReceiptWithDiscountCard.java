package CheckRunner.entity;

import CheckRunner.entity.receiptbuilder.TitleService;

public class CashReceiptWithDiscountCard implements CashReceipt {

    private TitleService titleService;

    private String title;
    private String content;
    private String ending;

    @Override
    public void printCashReceipt() {
//        TitleService titleService = new TitleService();
//        title = titleService.create1stString();
        System.out.println(title);
        System.out.println(content);
        System.out.println(ending);
    }
}
