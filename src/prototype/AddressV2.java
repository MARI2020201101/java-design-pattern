package prototype;

public class AddressV2 {
    public String streetAddress, city, country;

    public AddressV2(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    @Override
    public String toString() {
        return "AddressV2{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    //copy constructor
    public AddressV2(AddressV2 other){
        this(other.streetAddress, other.city, other.country);
    }
}
class Employee{
    public String name;
    public AddressV2 address;

    public Employee(String name, AddressV2 address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    //copy constructor
    public Employee(Employee other){
        this(other.name, new AddressV2(other.address));
    }
}
class Demo2{
    public static void main(String[] args) {
        Employee john = new Employee("john",new AddressV2("12-456","Brick City","London"));
        Employee jane = new Employee(john);
        jane.address.streetAddress = "987-211";
        jane.name = "jane";
        System.out.println(john);
        System.out.println(jane);
    }
}