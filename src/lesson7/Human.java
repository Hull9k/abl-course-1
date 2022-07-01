package lesson7;

public class Human {
    private String name;
    private int age;
    private int weight;

    private void name(String name) {
        this.name = name;
    }

    private void age(int age) {
        this.age = age;
    }

    private void weight(int weight) {
        this.weight = weight;
    }

    public void getInfo() {
        System.out.println(
                name + " - " +
                "возраст " + age +
                ", вес " + weight);
    }

    public static HumanBuilder builder() {
        return new HumanBuilder();
    }


    static class HumanBuilder {
        private final Human human;

        public HumanBuilder() {
            this.human = new Human();
        }

        public HumanBuilder name(String name) {
            this.human.name(name);
            return this;
        }

        public HumanBuilder age(int age) {
            this.human.age(age);
            return this;
        }

        public HumanBuilder weight(int weight) {
            this.human.weight(weight);
            return this;
        }

        public Human build() {
            return human;
        }
    }
}
