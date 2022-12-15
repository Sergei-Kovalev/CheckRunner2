package CheckRunner.entity;

public class DiscountCard {

    private int id;
    private int number;
    private double discountValue;

    public DiscountCard() {
    }

    public DiscountCard(int id, int number, double discountValue) {
        this.id = id;
        this.number = number;
        this.discountValue = discountValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }
}
