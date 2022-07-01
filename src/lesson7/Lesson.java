package lesson7;

public class Lesson {
    public static void main(String[] args) {

        Human human = Human
                .builder()
                .name("Петр")
                .age(20)
                .weight(80)
                .build();
        human.getInfo();

        System.out.println();
        new CarShop(new Car(5000)).sellCar();

        System.out.println();
        new CarShop(new Car(-1)).sellCar();
    }
}
