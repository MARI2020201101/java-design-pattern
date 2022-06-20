package decorator;

import java.util.function.Supplier;

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

class ColoredShapeV2<T extends Shape> implements Shape{
    private Shape shape;
    private String color;

    public ColoredShapeV2(Supplier<? extends T> constructor, String color) {
        this.shape = constructor.get();
        this.color = color;
    }
    @Override
    public String info() {
        return shape.info() + " with color "+ color;
    }
}

class TransparentShapeV2<T extends Shape> implements Shape{
    private Shape shape;
    private int transparency;

    public TransparentShapeV2(Supplier<? extends T> constructor, int transparency) {
        this.shape = constructor.get();
        this.transparency = transparency;
    }
    @Override
    public String info() {
        return shape.info() + " with transparency percent " + transparency + " %";
    }
}

class Demo2{
    public static void main(String[] args) {

        //dynamic decorator
        //항상 새로운 데코레이터를 만든다.
        //컴파일 타임 vs 런타임
        Circle circle = new Circle(10);
        System.out.println(circle.info());
        ColoredShape coloredShape = new ColoredShape(circle, "blue");
        System.out.println(coloredShape.info());
        Square square = new Square(20);
        TransparentShape transparentShape = new TransparentShape(square, 30);
        System.out.println(transparentShape.info());
        ColoredShape withTs = new ColoredShape(transparentShape, "green");
        System.out.println(withTs.info());

        System.out.println("=========================");
        //static decorator composition
        ColoredShapeV2<Circle> coloredShapeV2 = new ColoredShapeV2<>(
                ()->new Circle(100), "pink");
        System.out.println(coloredShapeV2.info());

        TransparentShapeV2<ColoredShapeV2<Square>> transparentShapeV2 = new TransparentShapeV2<>(
                ()-> new ColoredShapeV2<>(
                        ()-> new Square(1),"white")
                , 50);
        System.out.println(transparentShapeV2.info());
    }
}