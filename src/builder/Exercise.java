package builder;


import java.util.HashMap;
import java.util.Map;

class CodeBuilder{
    protected Map<String,String> props = new HashMap<>();
    protected String className;
    public CodeBuilder(String className)
    {
        this.className = className;
    }

    public CodeBuilder addField(String name, String type)
    {   props.put(name,type);
        return this;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("public class ")
                .append(className)
                .append("{\n");

        for(Map.Entry<String, String> entry : props.entrySet()){
            String name = entry.getKey();
            String type = entry.getValue();
            sb.append(String.format("\t%s %s;\n",type,name));
        }
        sb.append("}");
        return sb.toString();
    }
}


class Exercise {
    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("Person")
                .addField("name","String")
                .addField("age", "int");
        System.out.println(cb);
    }
}
