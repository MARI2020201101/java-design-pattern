package bridge;


interface RendererV2{
    public String whatToRenderAs();
}

abstract class ShapeV2 {
    private RendererV2 renderer;
    public String name;

    public ShapeV2(RendererV2 renderer) {
        this.renderer = renderer;
    }

    @Override
    public String toString() {
        return String.format("Drawing %s as %s", name, renderer.whatToRenderAs());
    }
}

class Triangle extends ShapeV2 {
    public Triangle(RendererV2 renderer) {
        super(renderer);
        name = "Triangle";
    }
}

class Square extends ShapeV2 {
    public Square(RendererV2 renderer) {
        super(renderer);
        name = "Square";
    }
}

class RasterRendererV2 implements RendererV2 {
    @Override
    public String whatToRenderAs() {
        return "pixels";
    }
}

class VectorRendererV2 implements RendererV2{
    @Override
    public String whatToRenderAs() {
        return "lines";
    }
}

class Exercise {
}
