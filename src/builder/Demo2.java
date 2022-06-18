package builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringJoiner;
class HtmlBuilder{
    private String rootName;
    private HtmlElement root = new HtmlElement();
    public HtmlBuilder(String rootName){
        this.rootName = rootName;
        this.root.name = rootName;
    }
    public HtmlBuilder addChild(String childName, String childText){
        HtmlElement e = new HtmlElement(childName,childText);
        root.elements.add(e);
        return this;
    }

    public void clear(){
        root = new HtmlElement();
        root.name = rootName;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
class HtmlElement{
    public String name, text;
    public ArrayList<HtmlElement> elements = new ArrayList<>();
    private final int indentSize = 2;
    private final String newLine =System.lineSeparator();

    public HtmlElement() {
    }

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    private String toStringImpl(int indent) {
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent * indentSize, " "));
        sb.append(String.format("%s<%s>%s", i, name, newLine));
        if (text != null && !text.isEmpty())
        {
            sb.append(String.join("", Collections.nCopies(indentSize*(indent+1), " ")))
                    .append(text)
                    .append(newLine);
        }
        return sb.toString();
    }
    @Override
    public String toString() {
        return toStringImpl(0);
    }

}
class Demo2 {

    public static void main(String[] args) {
        String hello = "hello";
        System.out.println("<p>" + hello + "</p>");
        String[] words = {"hello", "world", "hi"};//<p>를 일일이 concat해야하는 문제 발생
        StringJoiner sj = new StringJoiner("-","<p>","</p>");
        Arrays.stream(words).forEach(w -> sj.add(w));
        System.out.println("-------------");
        System.out.println(sj);

        String[] words2 = {"hello", "world", "hi","java"};
        StringBuilder sb = new StringBuilder();
        for (String word: words2) {
            sb.append("<p>");
            sb.append(word);
            sb.append("</p>");
        }
        System.out.println(sb);
        System.out.println("-------------");

        HtmlBuilder builder = new HtmlBuilder("ul");
        builder
                .addChild("li","hello")
                .addChild("li","world");

    }

}
