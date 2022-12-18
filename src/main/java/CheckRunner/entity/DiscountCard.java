package CheckRunner.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "discount_card")
public class DiscountCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "number")
    private int number;
    @Column(name = "discount_value")
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
