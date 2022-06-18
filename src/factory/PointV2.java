package factory;


class PointV2 {
    private double x,y;

    private PointV2(double x, double y){
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
     public static class PointFactory{
        public static PointV2 newCartesianPoint(double x, double y){
            return new PointV2(x, y);
        }
        public static PointV2 newPolarPoint(double x, double y){
            return new PointV2(x * Math.cos(y), x * Math.sin(y));
        }

    }
}


class Demo2{
    public static void main(String[] args) {
        PointV2 p = PointV2.PointFactory.newCartesianPoint(10,20);
        PointV2 p2 = PointV2.PointFactory.newPolarPoint(10,20);
        System.out.println(p);
        System.out.println(p2);
    }
}
