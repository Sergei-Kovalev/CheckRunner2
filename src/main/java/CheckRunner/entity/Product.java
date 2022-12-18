package CheckRunner.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "on_action")
    private boolean onAction;

    public Product() {
    }

    public Product(int id, String name, double price, boolean onAction) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.onAction = onAction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOnAction() {
        return onAction;
    }

    public void setOnAction(boolean onAction) {
        this.onAction = onAction;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", onAction=" + onAction +
                '}';
    }
}
