
public class Transaction {
    private double balance;

    public Transaction() {
        this.balance = 0;
    }

    public void insertMoney(double amount, double price) throws Exception {
        if (amount < price) {
            throw new Exception("Недостаточно средств");
        }
        else if (amount > 0) {
            balance += amount;
            System.out.printf("Внесено: %sP. Текущий баланс: %sP%n", amount, balance);
        } else {
            throw new Exception("Неверная сумма для внесения.");
        }
    }

    public void returnChange(double price) {
        balance -= price;
        if (balance > 0) {
            System.out.printf("Возврат сдачи: %sP%n", balance);
            balance = 0;
        } else {
            System.out.println("Нет сдачи для возврата.");
        }
    }
}