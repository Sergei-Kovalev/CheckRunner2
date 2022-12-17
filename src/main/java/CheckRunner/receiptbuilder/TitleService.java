package CheckRunner.receiptbuilder;

import CheckRunner.config.Config;
import CheckRunner.exceptions.InsufficientCashReceiptWidth;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TitleService {
    //ширина чека в символах для печати set in properties
    private static final int CASH_RECEIPT_WIDTH = Integer.parseInt(Config.getProperty(Config.CASH_RECEIPT_WIDTH));

    private static final String LINE_1 = "CASH RECEIPT";
    private static final String LINE_2 = "supermarket " + Config.getProperty(Config.SUPERMARKET_NUMBER);
    private static final String LINE_3 = Config.getProperty(Config.SUPERMARKET_ADDRESS);
    private static final String LINE_4 = "Tel : " + Config.getProperty(Config.SUPERMARKET_PHONE);
    private static final String LINE_6_1 = "CASHIER #" + Config.getProperty(Config.SUPERMARKET_CASHIER);
    private static final String LINE_6_2 = "DATE: " + createCurrentDate();
    private static final String LINE_7 = "TIME: " + createCurrentTime();

    public String addAllTitleStrings() {
        return createStringToCenter(LINE_1) + System.lineSeparator()
                + createStringToCenter(LINE_2) + System.lineSeparator()
                + createStringToCenter(LINE_3) + System.lineSeparator()
                + createStringToCenter(LINE_4) + System.lineSeparator()
                + System.lineSeparator()
                + createLine6() + System.lineSeparator()
                + createLine7() + System.lineSeparator()
                + "_".repeat(CASH_RECEIPT_WIDTH);

    }

    private static String createCurrentTime() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");
        return date.format(formatter);
    }

    private static String createCurrentDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    private static String createLine6() {

        StringBuilder stringBuilder = new StringBuilder();
        int width = CASH_RECEIPT_WIDTH - LINE_6_1.length() - LINE_6_2.length();

        if (width >= 0) {
            stringBuilder.append(LINE_6_1);
            stringBuilder.append(" ".repeat(width));
            stringBuilder.append(LINE_6_2);
        } else {
            throw new InsufficientCashReceiptWidth(CASH_RECEIPT_WIDTH);
        }
        return stringBuilder.toString();
    }

    private static String createLine7() {

        StringBuilder stringBuilder = new StringBuilder();
        int width = CASH_RECEIPT_WIDTH - LINE_6_2.length();

        if (width >= 0) {
            stringBuilder.append(" ".repeat(width));
            stringBuilder.append(LINE_7);
            stringBuilder.append(" ".repeat(LINE_6_2.length() - LINE_7.length()));
        } else {
            throw new InsufficientCashReceiptWidth(CASH_RECEIPT_WIDTH);
        }
        return stringBuilder.toString();
    }

    private static String createStringToCenter(String printedString) {

        StringBuilder stringBuilder = new StringBuilder();
        int width = (CASH_RECEIPT_WIDTH - printedString.length()) / 2;

        if (width >= 0) {
            stringBuilder.append(" ".repeat(width));
            stringBuilder.append(printedString);
            stringBuilder.append(" ".repeat(width));
        } else {
            throw new InsufficientCashReceiptWidth(CASH_RECEIPT_WIDTH);
        }
        return stringBuilder.toString();
    }



}
