package singleton;

import java.io.*;

class BasicSingleton implements Serializable{
    private static final BasicSingleton INSTANCE = new BasicSingleton();
    private BasicSingleton(){}
    private int value = 0;
    public static BasicSingleton getInstance(){
        return INSTANCE;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BasicSingleton{" +
                "value=" + value +
                '}';
    }
    //serialization 문제 해결하기
    protected Object readResolve(){
        return INSTANCE;
    }

}
class Demo{
    static void saveToFile(BasicSingleton singleton, String filename) throws FileNotFoundException {
        try(FileOutputStream fileout = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileout)) {
            out.writeObject(singleton);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static BasicSingleton readFromFile(String filename) throws IOException {
        try(FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn)){
            return (BasicSingleton) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws IOException {
        BasicSingleton basicSingleton = BasicSingleton.getInstance();
        System.out.println(basicSingleton);
        basicSingleton.setValue(123);
        System.out.println(basicSingleton);

        //Basic Singleton class 의 문제점
        // 1. reflection
        // 2. serialization
        String filename = "src/singleton/basic-singleton";
        saveToFile(basicSingleton, filename);
        BasicSingleton basicSingleton2 = readFromFile(filename);
        System.out.println("is same instance ? ==> " + (basicSingleton == basicSingleton2));
        System.out.println("is equal? ==> " + (basicSingleton.equals(basicSingleton2)));
        basicSingleton.setValue(111);
        System.out.println(basicSingleton.getValue());
        System.out.println(basicSingleton2.getValue());

    }
}