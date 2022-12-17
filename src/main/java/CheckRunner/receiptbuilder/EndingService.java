package CheckRunner.receiptbuilder;

import CheckRunner.config.Config;
import CheckRunner.entity.Product;
import CheckRunner.exceptions.InsufficientCashReceiptWidth;

import java.util.HashMap;

public class EndingService {
    private static final int CASH_RECEIPT_WIDTH = Integer.parseInt(Config.getProperty(Config.CASH_RECEIPT_WIDTH));

    private static final String TAXABLE_TOTAL_1 = "TAXABLE TOT.";
    private static final String VAT_1 = "VAT " + Config.getProperty(Config.VAT_VALUE) + "%";
    private static final String LAST_LINE_1 = "TOTAL:";

    private final HashMap<Product, Integer> listOfAllCheckPositions;

    public EndingService(HashMap<Product, Integer> listOfAllCheckPositions) {
        this.listOfAllCheckPositions = listOfAllCheckPositions;
    }

    public String addAllEndingStrings() {
        return System.lineSeparator()
                + createLineTaxableTotal() + System.lineSeparator()
                + createLineVat() + System.lineSeparator()
                + createLastLine();
    }

    private String getTaxableTotal() {
        CashReceiptCalculator calculator = new CashReceiptCalculator(listOfAllCheckPositions);
        return String.format("%.2f", calculator.calculateTotalForVat());
    }

    private String getVat() {
        CashReceiptCalculator calculator = new CashReceiptCalculator(listOfAllCheckPositions);
        return String.format("%.2f", calculator.calculateVat());
    }

    private String getTotal() {
        CashReceiptCalculator calculator = new CashReceiptCalculator(listOfAllCheckPositions);
        return String.format("%.2f", calculator.calculateTotal());
    }

    private String createLineTaxableTotal() {
        String taxableTotal_2 = "$" + getTaxableTotal();

        StringBuilder stringBuilder = new StringBuilder();
        int width = CASH_RECEIPT_WIDTH - TAXABLE_TOTAL_1.length() - taxableTotal_2.length();

        if (width >= 0) {
            stringBuilder.append(TAXABLE_TOTAL_1);
            stringBuilder.append(" ".repeat(width));
            stringBuilder.append(taxableTotal_2);
        } else {
            throw new InsufficientCashReceiptWidth(CASH_RECEIPT_WIDTH);
        }
        return stringBuilder.toString();
    }

    private String createLineVat() {
        String vat_2 = "$" + getVat();

        StringBuilder stringBuilder = new StringBuilder();
        int width = CASH_RECEIPT_WIDTH - VAT_1.length() - vat_2.length();

        if (width >= 0) {
            stringBuilder.append(VAT_1);
            stringBuilder.append(" ".repeat(width));
            stringBuilder.append(vat_2);
        } else {
            throw new InsufficientCashReceiptWidth(CASH_RECEIPT_WIDTH);
        }
        return stringBuilder.toString();
    }

    private String createLastLine() {
        String lastLine_2 = "$" + getTotal();

        StringBuilder stringBuilder = new StringBuilder();
        int width = CASH_RECEIPT_WIDTH - LAST_LINE_1.length() - lastLine_2.length();

        if (width >= 0) {
            stringBuilder.append(LAST_LINE_1);
            stringBuilder.append(" ".repeat(width));
            stringBuilder.append(lastLine_2);
        } else {
            throw new InsufficientCashReceiptWidth(CASH_RECEIPT_WIDTH);
        }
        return stringBuilder.toString();
    }

}
