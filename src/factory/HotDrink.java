package factory;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

interface HotDrink {
    void consume();
}
class Tea implements HotDrink{
    @Override
    public void consume() {
        System.out.println("Tea is delicious");
    }
}
class Coffee implements HotDrink{
    @Override
    public void consume() {
        System.out.println("Coffee is hot");
    }
}
interface HotDrinkFactory{
    HotDrink prepare(int amount);
}
class TeaFactory implements HotDrinkFactory{


    @Override
    public HotDrink prepare(int amount) {
        System.out.println("pour tea with amount..." + amount);
        System.out.println("add lemon....");
        return new Tea();
    }
}
class CoffeeFactory implements HotDrinkFactory{
    @Override
    public HotDrink prepare(int amount) {
        System.out.println("grind coffee with amount...."+ amount);
        System.out.println("pour hot water....");
        return new Coffee();
    }
}
class HotDrinkMachine{
    private List<Pair<String,HotDrinkFactory>> namedFactories = new ArrayList<>();
    public HotDrinkMachine(){


    }
}