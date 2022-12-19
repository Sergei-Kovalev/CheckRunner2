package CheckRunner.dao;

import CheckRunner.config.Config;
import CheckRunner.entity.DiscountCard;
import CheckRunner.entity.Product;
import CheckRunner.exceptions.InputTypeNotFountException;

import java.util.ArrayList;

public class DataPlaceholderHandler {

    public static ArrayList<Product> fillProductsList() {
        ArrayList<Product> productList = new ArrayList<>();
        if (Config.getProperty(Config.INPUT_TYPE).equalsIgnoreCase("class")) {
            FillDataFromClass.fillProductList(productList);
        } else if (Config.getProperty(Config.INPUT_TYPE).equalsIgnoreCase("file")) {
            FillDataFromFile.fillProductList(productList);
        } else if (Config.getProperty(Config.INPUT_TYPE).equalsIgnoreCase("postgresql")) {
            FillDataFromPostgreSQL.fillProductList(productList);
        } else {
            throw new InputTypeNotFountException(Config.getProperty(Config.INPUT_TYPE));
        }
         return productList;
    }

    public static ArrayList<DiscountCard> fillDiscountCardList() {
        ArrayList<DiscountCard> discountCardList = new ArrayList<>();
        if (Config.getProperty(Config.INPUT_TYPE).equalsIgnoreCase("class")) {
            FillDataFromClass.fillDiscountCardList(discountCardList);
        } else if (Config.getProperty(Config.INPUT_TYPE).equalsIgnoreCase("file")) {
            FillDataFromFile.fillDiscountCardList(discountCardList);
        } else if (Config.getProperty(Config.INPUT_TYPE).equalsIgnoreCase("postgresql")) {
            FillDataFromPostgreSQL.fillDiscountCardList(discountCardList);
        } else {
            throw new InputTypeNotFountException(Config.getProperty(Config.INPUT_TYPE));
        }
        return discountCardList;
    }
}
