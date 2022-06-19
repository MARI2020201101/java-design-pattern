package prototype;

import java.util.Arrays;

class Address implements Cloneable{
    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Address(streetName, houseNumber);
    }
}
class Person{
    public String[] names;
    public Address address;

    public Person(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "names=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //deep copy해야만 overwrite 되지 않는다.
        //전부 clone 한다... 그러나 권장되지 않는다

        return new Person(
                names.clone(),
                (Address) address.clone());
    }
}
class Demo{
    public static void main(String[] args) throws CloneNotSupportedException {
        Person john = new Person(new String[]{"John", "Smith"}
                , new Address("London Road", 123));
        Person jane = john; //참조를 그대로 clone 하면
        jane.names[0] = "Jane";
        jane.address.houseNumber = 987;
        System.out.println(john);
        System.out.println(jane); //overwrite 하게되는 문제가 발생한다.


        System.out.println("-----------------------------");
        Person john2 = new Person(new String[]{"John", "Smith"}
                , new Address("London Road", 123));

        Person jane2 = (Person) john2.clone();
        jane2.names[0] = "Jane";
        jane2.address.houseNumber = 987;
        System.out.println(john2);
        System.out.println(jane2);
    }
}