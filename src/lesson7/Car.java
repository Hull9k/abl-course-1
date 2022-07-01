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
            throw new RuntimeException("Стоимость < 0");
        }
    }
}
