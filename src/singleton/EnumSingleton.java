package singleton;

import java.io.*;

//초-간-단
//상속또한 불가능하며 serealization 에도 안전하다
enum EnumSingleton {
    INSTANCE;
    //이미 private한 constructor가 있으므로 굳이 생성하지 않아도 된다.
    EnumSingleton(){
        value = 42;
    }
    private int value;
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
class EnumSingletonTester{
    static void saveToFile(EnumSingleton singleton, String filename) throws FileNotFoundException {
        try(FileOutputStream fileout = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileout)) {
            out.writeObject(singleton);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static EnumSingleton readFromFile(String filename) throws IOException {
        try(FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn)){
            return (EnumSingleton) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws IOException {
        EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
        String filename = "src/singleton/enum-singleton";
        saveToFile(enumSingleton, filename);
        EnumSingleton enumSingleton2 = readFromFile(filename);
        System.out.println("is same instance ? ==> " + (enumSingleton == enumSingleton2));
        System.out.println("is equal? ==> " + (enumSingleton.equals(enumSingleton2)));
        enumSingleton.setValue(999);
        System.out.println(enumSingleton.getValue());
        System.out.println(enumSingleton2.getValue());
    }
}