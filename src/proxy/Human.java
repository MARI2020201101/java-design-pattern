package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

interface Human {
    void walk();
    void talk();
}
class Person implements Human{

    @Override
    public void walk() {
        System.out.println("I can walk");
    }

    @Override
    public void talk() {
        System.out.println("I can talk");
    }
}
class LoggingHandler implements InvocationHandler {
    private final Object target;
    private Map<String, Integer> calls = new HashMap<>();

    public LoggingHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();

        if(name.contains("toString")){
            return calls.toString();
        }

        calls.merge(name, 1, Integer::sum);
        return method.invoke(target, args);
    }
}
class Demo3{
    @SuppressWarnings("unchecked")
    public static <T> T withLogging(T target, Class<T> itf){
        return (T) Proxy.newProxyInstance(
                itf.getClassLoader(),
                new Class<?>[] {itf},
                new LoggingHandler(target)
        );
    }
    public static void main(String[] args) {
        Person person = new Person();
        Human withLogging = withLogging(person, Human.class);
        withLogging.talk();
        withLogging.talk();
        withLogging.walk();
        withLogging.walk();
        System.out.println(withLogging);
    }
}