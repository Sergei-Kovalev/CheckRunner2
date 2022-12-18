package CheckRunner.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static final String CASH_RECEIPT_WIDTH = "CashReceipt.width";
    public static final String SUPERMARKET_NUMBER = "TitleService.supermarket.number";
    public static final String SUPERMARKET_PHONE = "TitleService.supermarket.phone";
    public static final String SUPERMARKET_ADDRESS = "TitleService.supermarket.address";
    public static final String SUPERMARKET_CASHIER = "TitleService.supermarket.cashier";
    public static final String ACTION_DISCOUNT_VALUE = "ContentService.CashReceiptCalculator.actionDiscount";
    public static final String VAT_VALUE = "EndingService.vatValue";
    public static final String OUTPUT_TYPE = "CashReceiptOutputHandler.CONSOLE_or_FILE";
    public static final String INPUT_TYPE = "DataPlaceholderHandler.chooseDataSource.CLASS_FILE_or_POSTGRESQL";
    public static final String HIBERNATE_DRIVER_CLASS = "hibernate.connection.driver_class";
    public static final String HIBERNATE_URL = "hibernate.connection.url";
    public static final String HIBERNATE_USERNAME = "hibernate.connection.username";
    public static final String HIBERNATE_PASSWORD = "hibernate.connection.password";
    public static final String HIBERNATE_SESSION_CONTEXT_CLASS = "hibernate.current_session_context_class";
    public static final String HIBERNATE_DIALECT = "hibernate.dialect";

    private static final Properties properties = new Properties();

    public static synchronized String getProperty(String name) {
        if (properties.isEmpty()) {
            try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("application.properties")) {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return properties.getProperty(name);
    }
}
