package chainofresponsibility;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

// Command - Query - Segregation
class Event<Args> {
    private int index = 0;
    private Map<Integer, Consumer<Args>> handlers = new HashMap<>();

    public int subscribe(Consumer<Args> handler){
        int i = index;
        handlers.put(index++, handler);
        return i;
    }
    public void unsubscribe(int key){
        handlers.remove(key);
    }
    public void fire(Args args){
        for(Consumer<Args> handler : handlers.values()){
            handler.accept(args);
        }
    }
}
class Query {
    public String creatureName;
    enum Argument{
        ATTACK,DEFENSE;
    }
    public Argument argument;
    public int result;

    public Query(String creatureName, Argument argument, int result) {
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}
class Game{
    public Event<Query> queries = new Event<>();
}
class CreatureV2{
    private Game game;
    public String name;
    public int baseAttack,baseDefense;

    public CreatureV2(Game game, String name, int baseAttack, int baseDefense) {
        this.game = game;
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }

    int getAttack() {
        Query q = new Query(name, Query.Argument.ATTACK, baseAttack);
        game.queries.fire(q);
        return q.result;
    }

    int getDefense() {
        Query q = new Query(name, Query.Argument.DEFENSE, baseDefense);
        game.queries.fire(q);
        return q.result;
    }

    @Override
    public String toString() {
        return "CreatureV2{" +
                "game=" + game +
                ", name='" + name + '\'' +
                ", Attack=" + getAttack() +
                ", Defense=" + getDefense() +
                '}';
    }
}
class CreatureModifierV2{
    protected  Game game;
    protected Creature creature;

    public CreatureModifierV2(Game game, Creature creature) {
        this.game = game;
        this.creature = creature;
    }
}
class DoubleAttackModifierV2 extends CreatureModifierV2 implements AutoCloseable{

    private final int token;
    public DoubleAttackModifierV2(Game game, Creature creature) {
        super(game, creature);

        int token = game.queries.subscribe(
                q -> {
                    if (q.creatureName.equals(creature.name) &&
                            q.argument == Query.Argument.ATTACK) {
                        q.result *= 2;
                    }
                }
        );
        this.token = token;

    }

    @Override
    public void close() throws Exception {
        game.queries.unsubscribe(token);
    }
}
class IncreaseDefenseModifierV2 extends CreatureModifierV2 implements AutoCloseable{

    private final int token;
    public IncreaseDefenseModifierV2(Game game, Creature creature) {
        super(game, creature);

        int token = game.queries.subscribe(
                q -> {
                    if (q.creatureName.equals(creature.name) &&
                            q.argument == Query.Argument.DEFENSE) {
                        q.result += 2;
                    }
                }
        );
        this.token = token;

    }

    @Override
    public void close() throws Exception {
        game.queries.unsubscribe(token);
    }
}
class Demo2{
    public static void main(String[] args) {
        Game game = new Game();
        Creature goblin = new Creature("Goblin", 10, 10);
        System.out.println(goblin);

        IncreaseDefenseModifierV2 idm = new IncreaseDefenseModifierV2(game, goblin);

        try (DoubleAttackModifierV2 dam = new DoubleAttackModifierV2(game, goblin))
        {
            System.out.println(goblin);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(goblin);
    }
}