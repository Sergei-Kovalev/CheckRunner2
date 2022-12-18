package CheckRunner.receipts;

import CheckRunner.config.Config;
import CheckRunner.exceptions.OutputTypeNotFountException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class CashReceiptOutputHandler {

    String inputString;

    public CashReceiptOutputHandler(String inputString) {
        this.inputString = inputString;
    }

    public void chooseOutputTypeAndPrint() {
        if (Config.getProperty(Config.OUTPUT_TYPE).equalsIgnoreCase("console")) {
            System.out.println(inputString);
        } else if (Config.getProperty(Config.OUTPUT_TYPE).equalsIgnoreCase("file")) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("CheckReceipt.txt"))) {
                writer.write(inputString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new OutputTypeNotFountException(Config.getProperty(Config.OUTPUT_TYPE));
        }
    }
}
