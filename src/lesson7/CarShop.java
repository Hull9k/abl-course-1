package lesson7;

public class CarShop {
    private final Car car;

    public CarShop(Car car) {
        this.car = car;
    }

    public void sellCar() {
        System.out.println("Здравствуй клиент, цена этого авто");
        try {
            car.getCost();
            System.out.println("Хочешь купить авто?");
        } catch (Exception e) {
<<<<<<< HEAD
            System.out.println(e.getMessage());
=======
            System.out.println("Неизвестна мне");
>>>>>>> ed29062 (lesson 7)
            System.out.println("Давайте посмотрим другое авто?");
        }
    }
}
