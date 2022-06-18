package factory;
enum CoordinateSystem{
    CARTESIAN,
    POLAR
}
class Point {
    private double x,y;
//복잡해지는 constructor 어떻게 해결할것인가

//    private Point(double x, double y, CoordinateSystem cs) {
//        switch (cs){
//            case CARTESIAN:
//                this.x = x;
//                this.y = y;
//                break;
//            case POLAR:
//                this.x = x * Math.cos(y);
//                this.y = x * Math.sin(y);
//                break;
//        }
//    }
//
//    public static Point newCartesianPoint(double x, double y){
//        return new Point(x, y, CoordinateSystem.CARTESIAN);
//    }
//
//    public static Point newPolarPoint(double x, double y){
//        return new Point(x, y, CoordinateSystem.POLAR);
//    }

    //private으로 숨긴다
    private Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static Point newCartesianPoint(double x, double y){
        return new Point(x, y);
    }

    public static Point newPolarPoint(double x, double y){
        return new Point(x * Math.cos(y), x * Math.sin(y));
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
class Demo{
    public static void main(String[] args) {
        Point p = Point.newCartesianPoint(10,20);
        Point p2 = Point.newPolarPoint(10,20);
        System.out.println(p);
        System.out.println(p2);
    }
}
