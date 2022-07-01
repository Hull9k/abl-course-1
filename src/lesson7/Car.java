package lesson7;

public class Car {
    private final int cost;

    public Car(int cost) {
        this.cost = cost;
    }

    public void getCost() {
        if (this.cost > 0) {
            System.out.println(this.cost);
        } else {
<<<<<<< HEAD
            throw new RuntimeException("Неизвестна мне");
=======
            throw new RuntimeException("Стоимость < 0");
>>>>>>> ed29062 (lesson 7)
        }
    }
}
