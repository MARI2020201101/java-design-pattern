package composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GraphicObject {
    protected String name = "Group";
    public String color;
    public List<GraphicObject> children = new ArrayList<>();
    public GraphicObject(String name) {
        this.name = name;
    }

    public GraphicObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        print(sb, 0);
        return sb.toString();
    }

    private void print(StringBuilder stringBuilder,  int depth)
    {
        stringBuilder.append(String.join("", Collections.nCopies(depth, "*")))
                .append(depth > 0 ? " " : "")
                .append((color == null || color.isEmpty()) ? "" : color + " ")
                .append(getName())
                .append(System.lineSeparator());
        for (GraphicObject child : children)
            child.print(stringBuilder,  depth+1);
    }
}
class Circle extends GraphicObject{
    public Circle(String color){
        name = "Circle";
        this.color = color;
    }
}
class Square extends GraphicObject{
    public Square(String color){
        name = "Square";
        this.color = color;
    }
}
class Demo{
    public static void main(String[] args) {
        GraphicObject drawing = new GraphicObject();
        drawing.setName("My Drawing");
        drawing.children.add(new Square("red"));
        drawing.children.add(new Circle("blue"));

        GraphicObject group = new GraphicObject();
        group.children.add(new Square("white"));
        group.children.add(new Circle("pink"));

        GraphicObject group2 = new GraphicObject();
        group2.children.add(new Square("green"));
        group2.children.add(new Circle("yellow"));
        group.children.add(group2);
        drawing.children.add(group);

        System.out.println(drawing);
    }
}