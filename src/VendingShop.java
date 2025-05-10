import java.util.HashMap;

public class VendingShop {
    private final HashMap<String, Product> products;
    private double collectedMoney;
    private Product selectedProduct;

    public VendingShop() {
        this.products = new HashMap<>();
        this.collectedMoney = 0;
        this.selectedProduct = null;
        initializeProducts();
    }

    private void initializeProducts() {
        // Инициализация продуктов в автомате
        products.put("A1", new Product("Орехи", 100.00));
        products.put("A2", new Product("Вода", 15.50));
        products.put("B1", new Product("Шоколад", 70.00));
        products.put("B2", new Product("Bounty", 70.00));
        products.put("C1", new Product("Sushi", 400.00));
        products.put("C2", new Product("Cola", 50.00));
    }

    public void displayProducts() {
        System.out.println("\nДоступные продукты:");
        System.out.println("-------------------------------");
        for (HashMap.Entry<String, Product> entry : products.entrySet()) {
            System.out.printf("%s: %s Цена: %sP Осталось: %s%n",
                    entry.getKey(),
                    entry.getValue().getName(),
                    entry.getValue().getPrice(),
                    entry.getValue().getQuantity());
        }
        System.out.println("-------------------------------");
    }

    public double chooseProduct(String code) throws Exception {
        if (!products.containsKey(code)) {
            throw new Exception("Неверный код продукта.");
        }

        selectedProduct = products.get(code);
        if (selectedProduct.getQuantity() == 0) {
            throw new Exception("Извините, этот продукт закончился.");
        }

        System.out.printf("Необходимо внести %s рублей%n", selectedProduct.getPrice());

        return selectedProduct.getPrice();
    }

    public void decreaseProduct() {
            collectedMoney += selectedProduct.getPrice();
            selectedProduct.decreaseQuantity();
            System.out.printf("Вы купили: %s. Спасибо за покупку!%n", selectedProduct.getName());
            selectedProduct = null;
    }

    public void increaseProduct(String code, int amount) throws Exception {
        if (!products.containsKey(code)) {
            throw new Exception("Неверный код продукта.");
        }

        Product product = products.get(code);
        var quantity = product.getQuantity() + amount;

        if (quantity > 10) {
            throw new Exception("В автомат нельзя загрузить больше 10 продуктов");
        }

        product.increaseQuantity(amount);
        System.out.printf("Количество новых продуктов в автомате: %s%n", amount);
    }
}