package solid.srp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Journal {
    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    //journal 의 책임(할일)
    public void addEntry(String text){
        entries.add(""+(++count)+" : " + text);
    }
    public void removeEntry(int index) {
        entries.remove(index);
    }

    //journal의 책임위반 (-> 다른 클래스로 분리한다.)
    public void save(String filename) throws FileNotFoundException {
        try(PrintStream out = new PrintStream(filename)){
            out.println(toString());
        }
    }

    @Override
    public String toString() {
        return "Journal{" +
                "entries=" + entries +
                '}';
    }
}

//다른 클래스로 책임을 분리한다.
class Persistence{

    public void saveToFile(Journal journal,String filename, boolean overwrite){

        if(overwrite || new File(filename).exists()){
            try(PrintStream out = new PrintStream(filename)){
                out.println(journal.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}



class Demo{
    public static void main(String[] args) {
        Journal j = new Journal();
        j.addEntry("hello");
        j.addEntry("hello 2");
        j.addEntry("hello 3");
        System.out.println(j);

        Persistence p = new Persistence();
        String filename = "/Users/mari/for-me/java-design-pattern/src/solid/srp/newfile.txt";
        p.saveToFile(j,filename,true);
    }
}

