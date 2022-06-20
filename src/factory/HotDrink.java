package factory;

import org.javatuples.Pair;
import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public HotDrinkMachine() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Set<Class<? extends HotDrinkFactory>> types = new Reflections("factory")
                .getSubTypesOf(HotDrinkFactory.class);
        for (Class<? extends HotDrinkFactory> type : types) {
            namedFactories.add(new Pair<>(type.getSimpleName().replace("Factory", ""),
                    type.getDeclaredConstructor().newInstance()));
        }

    }

    public HotDrink makeDrink() throws Exception{

        System.out.println("Available drinks --> \n");
        for (int index = 0; index < namedFactories.size(); ++index)
        {
            Pair<String, HotDrinkFactory> item = namedFactories.get(index);
            System.out.println("" + index + ": " + item.getValue0());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            String s;
            int i, amount;
            if ((s = reader.readLine()) != null
                    && (i = Integer.parseInt(s)) >= 0
                    && i < namedFactories.size())
            {
                System.out.println("Specify amount: ");
                s = reader.readLine();
                if (s != null
                        && (amount = Integer.parseInt(s)) > 0)
                {
                    return namedFactories.get(i).getValue1().prepare(amount);
                }
            }
            System.out.println("Incorrect input, try again.");
        }

    }
}
class AbstractFactoryDemo
{
    public static void main(String[] args) throws Exception
    {
        HotDrinkMachine machine = new HotDrinkMachine();
        HotDrink drink = machine.makeDrink();
        drink.consume();
    }
}