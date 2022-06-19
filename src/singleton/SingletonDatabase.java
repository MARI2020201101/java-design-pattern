package singleton;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

class SingletonDatabase implements Database{
    private Dictionary<String, Integer> capitals = new Hashtable<>();
    private static int instanceCount = 0;
    private static int getCount(){
        return instanceCount;
    }
    private SingletonDatabase(){
        instanceCount++;
        System.out.println("initializing database....");

        try {
            File file = new File(SingletonDatabase.class.getProtectionDomain()
                    .getCodeSource().getLocation().getPath());

            Path fullPath = Paths.get(file.getPath(), "capitals.txt");
            List<String> lines = Files.readAllLines(fullPath);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static final SingletonDatabase INSTANCE = new SingletonDatabase();

    public static SingletonDatabase getInstance() {
        return INSTANCE;
    }
    public int getPopulation(String name){
        return capitals.get(name);
    }
}
interface Database{
    int getPopulation(String name);
}

class ConfigurableRecordFinder{
    private Database database;
    public ConfigurableRecordFinder(Database database){
        this.database = database;
    }
    public int getTotalPopulation(List<String> names){
        int result = 0;
        for(String name : names){
            result += database.getPopulation(name);
        }
        return result;
    }
}
class DummyDatabase implements Database{
    //통합테스트 대신 dummy데이터를 가진 db class를 작성하여 유닛테스트를 할수있다..!!
    //value를 내가 알고 있으니 훨씬 명시적! 쉽다!!
    private Dictionary<String,Integer> capitals = new Hashtable<>();
    public DummyDatabase(){
        capitals.put("Seoul",10000);
        capitals.put("Mexico",20000);
        capitals.put("Canada",30000);
    }
    @Override
    public int getPopulation(String name) {
        return capitals.get(name);
    }
}
class SingletonRecordFinder{
    public int getTotalPopulation(List<String> names){
        int result = 0;
        for(String name : names){
            //실제 통합테스트
            result += SingletonDatabase.getInstance().getPopulation(name);
        }
        return result;
    }
}
class Tests{
    static void singletonTotalPopulationTest(){

        ConfigurableRecordFinder rf = new ConfigurableRecordFinder(new DummyDatabase());
        List<String> names = List.of("Seoul", "Mexico", "Canada");
        int totalPopulation = rf.getTotalPopulation(names);
        System.out.println("totalPopulation is? "+ (10000 + 20000 +30000) +" == " + totalPopulation );
    }
    public static void main(String[] args) {
        singletonTotalPopulationTest();
    }
}