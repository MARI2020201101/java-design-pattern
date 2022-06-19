package factory;

import java.util.concurrent.atomic.AtomicInteger;

class Person {
    public int id;
    public String name;

    public Person(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
class PersonFactory {

    private AtomicInteger index = new AtomicInteger(0);
    public Person createPerson(String name)
    {
        int id = index.getAndAdd(1);
        return new Person(id, name);
    }
}
class Demo3{
    public static void main(String[] args) {
        PersonFactory personFactory = new PersonFactory();
        Person jina = personFactory.createPerson("jina");
        Person momo = personFactory.createPerson("momo");
        Person rika = personFactory.createPerson("rika");
        System.out.println(jina);
        System.out.println(momo);
        System.out.println(rika);
    }
}
