package solid.ocp;

import java.util.List;
import java.util.stream.Stream;

enum Color {
    RED,BLUE,GREEN,PINK,WHITE
}
enum Size{
    XS,S,M,L,XL
}
class Product{
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", size=" + size +
                '}';
    }
}
class ProductFilter{
    //조건이 늘어날수록 메서드도 불어난다....
    public Stream<Product> filterByColor(List<Product> productList, Color color){
        return productList.stream().filter(p -> p.color == color);
    }
    public Stream<Product> filterBySize(List<Product> productList, Size size){
        return productList.stream().filter(p -> p.size == size);
    }
    public Stream<Product> filterByColorAndSize(List<Product> productList, Color color, Size size){
        return productList.stream().filter(p -> p.color == color && p.size == size);
    }
}

interface Specification<T>{
    boolean isSatisfied(T item);
}
interface Filter<T>{
    Stream<T> filter(List<T> items, Specification<T> spec);
}
class ColorSpecification implements Specification<Product>{
    private Color color;
    public ColorSpecification(Color color){
        this.color=color;
    }
    @Override
    public boolean isSatisfied(Product item) {
        return item.color==color;
    }
}
class SizeSpecification implements Specification<Product>{
    private Size size;
    public SizeSpecification(Size size){
        this.size=size;
    }
    @Override
    public boolean isSatisfied(Product item) {
        return item.size==size;
    }
}

class AndSpecification<T> implements Specification<T>{

    private Specification<T> first, second;
    public AndSpecification(Specification<T> first, Specification<T> second){
        this.first=first;
        this.second=second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}

class BetterFilter implements Filter<Product>{
    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(i-> spec.isSatisfied(i));
    }
}

class Demo{
    public static void main(String[] args) {
        Product a = new Product("Apple", Color.RED, Size.M);
        Product b = new Product("Tree", Color.GREEN, Size.L);
        Product c = new Product("House", Color.WHITE, Size.XL);
        List<Product> productList = List.of(a,b,c);

        ProductFilter productFilter = new ProductFilter();
        productFilter.filterByColor(productList, Color.GREEN).forEach(System.out::println);
        productFilter.filterBySize(productList, Size.M).forEach(System.out::println);
        productFilter.filterByColorAndSize(productList, Color.WHITE, Size.XL).forEach(System.out::println);

        System.out.println("============================================");
        ColorSpecification colorSpecification = new ColorSpecification(Color.RED);
        SizeSpecification sizeSpecification = new SizeSpecification(Size.XL);
        BetterFilter betterFilter = new BetterFilter();
        betterFilter.filter(productList, colorSpecification).forEach(System.out::println);
        betterFilter.filter(productList, sizeSpecification).forEach(System.out::println);

        System.out.println("----------------------------------------------");
        betterFilter.filter(productList
                , new AndSpecification<>(new ColorSpecification(Color.GREEN), new SizeSpecification(Size.L)))
                .forEach(System.out::println);
    }
}
