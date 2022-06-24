package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Persons{
    String drink();
    String drive();
    String drinkAndDrive();
}
class PersonV2 implements Persons{
    private int age;
    public PersonV2(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String drink() {
        return "drinking";
    }

    public String drive() {
        return "driving";
    }

    public String drinkAndDrive() {
        return "driving while drunk";
    }
}
class ResponsiblePerson implements InvocationHandler {
    private Persons person;
    private boolean isOlderThan18;
    private boolean isOlderThan16;

    public ResponsiblePerson(PersonV2 person) {
        this.person = person;
        if(person.getAge() >=18) {
            isOlderThan18=isOlderThan16=true;
        }
        else if(person.getAge() >=16){
            isOlderThan18=true;
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        if(name.equals("drink") && !isOlderThan18){
            return "too young";
        }
        else if(name.equals("drive") && !isOlderThan16){
            return "too young";
        }
        else if(name.equals("drinkAndDrive")){
            return "dead";
        }
        return method.invoke(person,args);
    }
}
class PersonProxyDemo{
    public static void main(String[] args) {
        PersonV2 person = new PersonV2(17);
        Persons instance = (Persons) Proxy.newProxyInstance(ResponsiblePerson.class.getClassLoader(),
                new Class[]{Persons.class},
                new ResponsiblePerson(person));
        System.out.println(instance.drink());
        System.out.println(instance.drive());
        System.out.println(instance.drinkAndDrive());

    }
}
