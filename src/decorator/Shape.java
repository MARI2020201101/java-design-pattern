package decorator;

interface Shape {
    String info();
}
class Circle implements Shape{

    private float radius;
    public Circle(float radius) {
        this.radius = radius;
    }
    public Circle(){}
    void resize(float factor){
        this.radius *= factor;
    }
    @Override
    public String info() {
        return "A Circle of radius "+ radius;
    }
}
class Square implements Shape{

    private float side;
    public Square(float side) {
        this.side = side;
    }
    public Square(){}

    @Override
    public String info() {
        return "A Square of side "+ side;
    }
}
class ColoredShape implements Shape{

    private Shape shape;
    private String color;

    public ColoredShape(Shape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " with color "+ color;
    }
}
class TransparentShape implements Shape{
    private Shape shape;
    private int transparency;

    public TransparentShape(Shape shape, int transparency) {
        this.shape = shape;
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " with transparency percent " + transparency + " %";
    }
}
class Demo2{
    public static void main(String[] args) {
        Circle circle = new Circle(10);
        System.out.println(circle.info());
        ColoredShape coloredShape = new ColoredShape(circle, "blue");
        System.out.println(coloredShape.info());
        Square square = new Square(20);
        TransparentShape transparentShape = new TransparentShape(square, 30);
        System.out.println(transparentShape.info());
        ColoredShape withTs = new ColoredShape(transparentShape, "green");
        System.out.println(withTs.info());
    }
}