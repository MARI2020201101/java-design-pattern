package proxy;


import java.util.Objects;

class Property<T>{
    private T value;
    public Property(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property<?> property = (Property<?>) o;
        return Objects.equals(value, property.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
class Creature{
    //direct access 를 막는다.
    private Property<Integer> agility = new Property<>(10);
    public Creature() {
    }
    public int getAgility() {
        return agility.getValue();
    }
    public void setAgility(int agility) {
        this.agility.setValue(agility);
    }
}

class Demo2 {
    public static void main(String[] args) {
        Creature creature = new Creature();
        creature.setAgility(111);
        System.out.println(creature.getAgility());
    }
}
