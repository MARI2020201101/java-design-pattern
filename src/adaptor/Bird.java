package adaptor;

class Bird {
    public int age;

    public String fly() {
        return age < 10 ? "flying" : "too old";
    }
}

class Lizard {
    public int age;

    public String crawl() {
        return (age > 1) ? "crawling" : "too young";
    }
}

class Dragon {
    private int age;
    private Bird bird = new Bird();
    private Lizard lizard = new Lizard();

    public Dragon(int age){
        this.age = age;
        bird.age = age;
        lizard.age = age;
    }

    public Dragon() {
    }

    public void setAge(int age) {
        this.age = age;
        bird.age = age;
        lizard.age = age;
    }
    public String fly() {
       return bird.fly();
    }
    public String crawl() {
       return lizard.crawl();
    }
}
class DragonTester{
    public static void main(String[] args) {
        Dragon dragon = new Dragon();
        dragon.setAge(10);
        System.out.println(dragon.crawl());
        System.out.println(dragon.fly());

        System.out.println("=============");
        Dragon dragon2 = new Dragon();
        dragon2.setAge(1);
        System.out.println(dragon2.crawl());
        System.out.println(dragon2.fly());
    }
}