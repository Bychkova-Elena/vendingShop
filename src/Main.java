public class Main {
    public static void main(String[] args) {

        VendingShop vendingShop = new VendingShop();
        Person person = new Person("Ivan", 100.00);

        vendingShop.displayProducts();

//        Неверный код продукта
//        try {
//            vendingShop.chooseProduct("C3");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

//        Недостаточно средств
//        try {
//            double price = vendingShop.chooseProduct("C1");
//            Transaction transaction = new Transaction();
//
//            transaction.insertMoney(person.sum(), price);
//            vendingShop.decreaseProduct();
//            transaction.returnChange(price);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        try {
            double price = vendingShop.chooseProduct("A2");
            Transaction transaction = new Transaction();

            transaction.insertMoney(person.sum(), price);
            vendingShop.decreaseProduct();
            transaction.returnChange(price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        vendingShop.displayProducts();

//        В автомат нельзя загрузить больше 10 продуктов
//        try {
//            vendingShop.increaseProduct("A1", 1);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        try {
            vendingShop.increaseProduct("A2", 1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        vendingShop.displayProducts();
    }
}