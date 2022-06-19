package singleton;

import java.io.File;
import java.io.IOException;

class StaticBlockSingleton {
    private StaticBlockSingleton() throws IOException {
        System.out.println("StaticBlockSingleton is initializing...");
        File.createTempFile(".",".");
    }

    private static StaticBlockSingleton INSTANCE;

    static {
        try{
            INSTANCE = new StaticBlockSingleton();
        }catch (Exception e){
            System.out.println("Failed to create singleton...");
        }
    }
    public static StaticBlockSingleton getInstance(){
        return INSTANCE;
    }
}
class StaticBlockSingletonTester{
    public static void main(String[] args) {
        StaticBlockSingleton staticBlockSingleton = StaticBlockSingleton.getInstance();
        System.out.println(staticBlockSingleton);
    }
}
