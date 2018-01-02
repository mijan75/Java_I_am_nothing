public class Point {
    private double x;
    private double y;
    private double d1;
    private double d2;
    private double d3;
    private int clusterClass;


    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getD1() {
        return d1;
    }

    public void setD1(double d1) {
        this.d1 = d1;
    }

    public double getD2() {
        return d2;
    }

    public void setD2(double d2) {
        this.d2 = d2;
    }

    public double getD3() {
        return d3;
    }

    public void setD3(double d3) {
        this.d3 = d3;
    }

    public int getClusterClass() {
        return clusterClass;
    }

    public void setClusterClass(int clusterClass) {
        this.clusterClass = clusterClass;
    }

    public String getString(){
        StringBuilder sb = new StringBuilder();
        sb.append(x +" ");
        sb.append(y+" ");
        sb.append(""+clusterClass);
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", d1=" + d1 +
                ", d2=" + d2 +
                ", d3=" + d3 +
                ", clusterClass=" + clusterClass +
                '}';
    }
}
