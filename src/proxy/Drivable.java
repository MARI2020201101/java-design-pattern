package proxy;

interface Drivable {
    void drive();
}
class Car implements Drivable{
    protected  Driver driver;
    public Car(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void drive() {
        System.out.println("Car being driven");
    }
}
class CarProxy extends Car{

    public CarProxy(Driver driver) {
        super(driver);
    }
    @Override
    public void drive() {
        if(driver.age >= 16){
            super.drive();
        }else{
            System.out.println("Driver too young to drive...");
        }
    }
}
class Driver{
    public int age;

    public Driver(int age) {
        this.age = age;
    }
}
class Demo{
    public static void main(String[] args) {
        Driver driver = new Driver(10);
        Driver driver2 = new Driver(20);
        Car car = new CarProxy(driver);
        car.drive();
        new CarProxy(driver2).drive();
    }
}