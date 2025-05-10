package interfaces;

public interface IProduct {
    String getName();

    double getPrice();

    void setPrice(double price);

    int getQuantity();

    void decreaseQuantity();

    void increaseQuantity(int amount);

}