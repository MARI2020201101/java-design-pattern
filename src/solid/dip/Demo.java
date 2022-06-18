package solid.dip;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

enum Relationship{
    PARENT,
    CHILD,
    SIBLING
}
class Person{
    public String name;
    public Person(String name){
        this.name=name;
    }
}
interface RelationshipBrowser{
    List<Person> findAllChildrenOf(String name);
}

class Relationships implements RelationshipBrowser{
    //low level 모듈 . 비지니스 로직이 들어가면 안된다.
    private List<Triplet<Person,Relationship,Person>> relations = new ArrayList<>();
    public void addParentAndChild(Person parent, Person child){
        relations.add(new Triplet<>(parent,Relationship.PARENT,child));
        relations.add(new Triplet<>(child,Relationship.CHILD,parent));
    }

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations
                .stream()
                .filter(x -> x.getValue0().name.equals(name)
                        && x.getValue1() == Relationship.PARENT)
                .map(Triplet::getValue2)
                .collect(Collectors.toList());
    }
}
class Research{
    //high level 모듈 . relationships의 내부 프로퍼티에 의존하고 있다. List가 아니라 다른 컬렉션으로 바뀐다면?
    // high level 모듈이 너무 많은 것을 알고있어야 함

//    public Research(Relationships relationships){
//        relationships
//                .getRelations()
//                .stream()
//                .filter(x -> x.getValue0().name.equals("John")
//                && x.getValue1() == Relationship.PARENT)
//                .forEach(x-> System.out.println("" +
//                        "John has child : " +
//                        ""+ x.getValue2().name));
//    }

    public Research(RelationshipBrowser browser){
        browser.findAllChildrenOf("John")
                .stream()
                .forEach(x -> System.out.println("John has child : " +x.name ));
    }
}
class Demo {
    public static void main(String[] args) {
        /*
        Dependency Inversion Principle
        - high level 모듈이 low level 모듈에게 의존해서는 안된다
        */

        Person parent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Martin");

        Relationships relationships = new Relationships();

        relationships.addParentAndChild(parent,child1);
        relationships.addParentAndChild(parent,child2);

        Research research = new Research(relationships);
    }
}
