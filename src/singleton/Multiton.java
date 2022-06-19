package singleton;

import java.util.HashMap;

enum SubSystem{
    PRIMARY,
    AUXILIARY,
    FALLBACK
}
class Printer{
    private static int instanceCount = 0;
    private Printer(){
        instanceCount++;
        System.out.println("total instance number : " + instanceCount);

    }
    private static HashMap<SubSystem, Printer> instances = new HashMap<>();
    public static Printer get(SubSystem ss){
        if(instances.containsKey(ss)){
            return instances.get(ss);
        }
        Printer instance = new Printer();
        instances.put(ss, instance);
        return instance;
    }
}

public class Multiton {
    //key - value store 를 저장하여 만들고자하는 싱글톤의 수를 제한한다.
    public static void main(String[] args) {
        //만들어지는 instance 의 number 를 제한하거나. lazy를 하거나. 제약을 둔다
        Printer main = Printer.get(SubSystem.PRIMARY);
        Printer aux = Printer.get(SubSystem.AUXILIARY);
        Printer fall = Printer.get(SubSystem.FALLBACK);
        Printer aux2 = Printer.get(SubSystem.AUXILIARY);

    }
}
