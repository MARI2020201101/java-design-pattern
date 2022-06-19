package singleton;

public class LazySingleton {
    private static LazySingleton INSTANCE;
    private LazySingleton(){
        System.out.println("initializing a lazy singleton....");
    }
    //thread - safe하도록 synchronized를 붙인다
    public static synchronized LazySingleton getInstance(){
        if(INSTANCE == null){
            INSTANCE = new LazySingleton();
        }
        //race condition 문제가 있다 - 여러 스레드에서 동시에 부르면 체크할때는 NULL이므로  둘다 생성됨
        return INSTANCE;
    }

    // double - checked locking 기법
    public static LazySingleton getInstanceDoubleCheck(){
        if(INSTANCE == null){
            synchronized (LazySingleton.class){
                if(INSTANCE==null){
                    INSTANCE = new LazySingleton();
                }
            }
        }
        return INSTANCE;
    }
}
