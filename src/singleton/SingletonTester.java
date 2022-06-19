package singleton;

import java.util.function.Supplier;

class SingletonTester {
    public static boolean isSingleton(Supplier<Object> func)
    {
        Object o1 = func.get();
        Object o2 = func.get();
        return o1 == o2;
    }
}
