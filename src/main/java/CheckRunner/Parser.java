package CheckRunner;

import CheckRunner.entity.DiscountCard;
import CheckRunner.entity.Product;
import CheckRunner.exceptions.DiscountCardNotFountException;
import CheckRunner.exceptions.ProductNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    private final String[] args;

    public Parser(String[] args) {
        this.args = args;
    }

    public DiscountCard findDiscountCardByNumber(int discountCardNumber, List<DiscountCard> allDiscountCards) {
        DiscountCard discountCard = null;
        for (DiscountCard card : allDiscountCards) {
            if (card.getNumber() == discountCardNumber){
                discountCard = card;
            }
        }
        if (discountCard == null) {
            throw new DiscountCardNotFountException(discountCardNumber);
        }
        return discountCard;
    }

    public HashMap<Product, Integer> replaceIdToProduct(List<Product> allProducts, HashMap<Integer, Integer> map) {
        HashMap<Product, Integer> listOfProductsAndTheirQuantity = new HashMap<>();
        for (Integer productId : map.keySet()) {
            Product product = findProductById(productId, allProducts);
            listOfProductsAndTheirQuantity.put(product, map.get(productId));
        }
        return listOfProductsAndTheirQuantity;
    }

    public Product findProductById(int id, List<Product> allProducts) {
        Product product = null;
        for (Product prod : allProducts) {
            if (prod.getId() == id){
                product = prod;
            }
        }
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }

    public HashMap<Integer, Integer> getAllCheckLines() {
        HashMap<Integer, Integer> map = new HashMap<>();
        if (findDiscountCardNumber() == 0) {
            for (String a : args) {
                String[] str =  a.split("-");
                addItemAndQuantityToMap(str, map);
            }
        } else {
            for (int i = 0; i < args.length - 1; i++) {
                String[] str =  args[i].split("-");
                addItemAndQuantityToMap(str, map);
            }
        }
        return map;
    }

    private void addItemAndQuantityToMap(String[] str, Map<Integer, Integer> map) {
        int itemId = Integer.parseInt(str[0]);
        int quantity = Integer.parseInt(str[1]);
        map.put(itemId, quantity);
    }

    public int findDiscountCardNumber() {
        String lastArg = args[args.length -1];
        if (lastArg.startsWith("card")) {
            String[] lastArgSplitted = lastArg.split("-");
            return Integer.parseInt(lastArgSplitted[1]);
        } else {
            return 0;
        }
    }
}
