package chainofresponsibility;

import java.util.ArrayList;
import java.util.List;

abstract class CreatureV3 {
    public abstract int getAttack();
    public abstract int getDefense();
}

class Goblin extends CreatureV3 {

    protected int attack, defense;
    private GameV2 game;
    public Goblin(GameV2 game) {
        game.creatures.add(this);
        this.game=game;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public String toString() {
        return "Goblin [ "+
                "attack : "+ getAttack() +
                "\ndefense : "+getDefense() +" ]";
    }
}

class GoblinKing extends Goblin {
    public GoblinKing(GameV2 game) {
        super(game);
        for(CreatureV3 creature : game.creatures){
            if(creature instanceof Goblin){

            }

        }
    }
}

enum Statistic {
    ATTACK, DEFENSE
}

class GameV2 {
    public List<CreatureV3> creatures = new ArrayList<>();
}
class Exercise {
}
